#!/usr/bin/env python
# Example of producing histograms using matplotlib
# Tested with Python 2.7
# NB: uses hard-wired path for input file 'file' below

"""
Simple demo of a horizontal bar chart.
"""

import sys
import random
import json      # for reading/writing JSON files
import os.path   # for isfile etc

# import numpy as np
import matplotlib.pyplot as plt

# -----------------------------------------------------------------------------
# constants

continents = {
  'AF': 'Africa',
  'AS': 'Asia',
  'EU': 'Europe',
  'NA': 'North America',
  'SA': 'South America',
  'OC': 'Oceania',
  'AN': 'Antarctica'
}

cntry_to_cont = {
  'AF': 'AS',
  'AX': 'EU',
  'AL': 'EU',
  'DZ': 'AF',
  'AS': 'OC',
  'AD': 'EU',
  'AO': 'AF',
  'AI': 'NA',
  'AQ': 'AN',
  'AG': 'NA',
  'AR': 'SA',
  'AM': 'AS',
  'AW': 'NA',
  'AU': 'OC',
  'AT': 'EU',
  'AZ': 'AS',
  'BS': 'NA',
  'BH': 'AS',
  'BD': 'AS',
  'BB': 'NA',
  'BY': 'EU',
  'BE': 'EU',
  'BZ': 'NA',
  'BJ': 'AF',
  'BM': 'NA',
  'BT': 'AS',
  'BO': 'SA',
  'BQ': 'NA',
  'BA': 'EU',
  'BW': 'AF',
  'BV': 'AN',
  'BR': 'SA',
  'IO': 'AS',
  'VG': 'NA',
  'BN': 'AS',
  'BG': 'EU',
  'BF': 'AF',
  'BI': 'AF',
  'KH': 'AS',
  'CM': 'AF',
  'CA': 'NA',
  'CV': 'AF',
  'KY': 'NA',
  'CF': 'AF',
  'TD': 'AF',
  'CL': 'SA',
  'CN': 'AS',
  'CX': 'AS',
  'CC': 'AS',
  'CO': 'SA',
  'KM': 'AF',
  'CD': 'AF',
  'CG': 'AF',
  'CK': 'OC',
  'CR': 'NA',
  'CI': 'AF',
  'HR': 'EU',
  'CU': 'NA',
  'CW': 'NA',
  'CY': 'AS',
  'CZ': 'EU',
  'DK': 'EU',
  'DJ': 'AF',
  'DM': 'NA',
  'DO': 'NA',
  'EC': 'SA',
  'EG': 'AF',
  'SV': 'NA',
  'GQ': 'AF',
  'ER': 'AF',
  'EE': 'EU',
  'ET': 'AF',
  'FO': 'EU',
  'FK': 'SA',
  'FJ': 'OC',
  'FI': 'EU',
  'FR': 'EU',
  'GF': 'SA',
  'PF': 'OC',
  'TF': 'AN',
  'GA': 'AF',
  'GM': 'AF',
  'GE': 'AS',
  'DE': 'EU',
  'GH': 'AF',
  'GI': 'EU',
  'GR': 'EU',
  'GL': 'NA',
  'GD': 'NA',
  'GP': 'NA',
  'GU': 'OC',
  'GT': 'NA',
  'GG': 'EU',
  'GN': 'AF',
  'GW': 'AF',
  'GY': 'SA',
  'HT': 'NA',
  'HM': 'AN',
  'VA': 'EU',
  'HN': 'NA',
  'HK': 'AS',
  'HU': 'EU',
  'IS': 'EU',
  'IN': 'AS',
  'ID': 'AS',
  'IR': 'AS',
  'IQ': 'AS',
  'IE': 'EU',
  'IM': 'EU',
  'IL': 'AS',
  'IT': 'EU',
  'JM': 'NA',
  'JP': 'AS',
  'JE': 'EU',
  'JO': 'AS',
  'KZ': 'AS',
  'KE': 'AF',
  'KI': 'OC',
  'KP': 'AS',
  'KR': 'AS',
  'KW': 'AS',
  'KG': 'AS',
  'LA': 'AS',
  'LV': 'EU',
  'LB': 'AS',
  'LS': 'AF',
  'LR': 'AF',
  'LY': 'AF',
  'LI': 'EU',
  'LT': 'EU',
  'LU': 'EU',
  'MO': 'AS',
  'MK': 'EU',
  'MG': 'AF',
  'MW': 'AF',
  'MY': 'AS',
  'MV': 'AS',
  'ML': 'AF',
  'MT': 'EU',
  'MH': 'OC',
  'MQ': 'NA',
  'MR': 'AF',
  'MU': 'AF',
  'YT': 'AF',
  'MX': 'NA',
  'FM': 'OC',
  'MD': 'EU',
  'MC': 'EU',
  'MN': 'AS',
  'ME': 'EU',
  'MS': 'NA',
  'MA': 'AF',
  'MZ': 'AF',
  'MM': 'AS',
  'NA': 'AF',
  'NR': 'OC',
  'NP': 'AS',
  'NL': 'EU',
  'NC': 'OC',
  'NZ': 'OC',
  'NI': 'NA',
  'NE': 'AF',
  'NG': 'AF',
  'NU': 'OC',
  'NF': 'OC',
  'MP': 'OC',
  'NO': 'EU',
  'OM': 'AS',
  'PK': 'AS',
  'PW': 'OC',
  'PS': 'AS',
  'PA': 'NA',
  'PG': 'OC',
  'PY': 'SA',
  'PE': 'SA',
  'PH': 'AS',
  'PN': 'OC',
  'PL': 'EU',
  'PT': 'EU',
  'PR': 'NA',
  'QA': 'AS',
  'RE': 'AF',
  'RO': 'EU',
  'RU': 'EU',
  'RW': 'AF',
  'BL': 'NA',
  'SH': 'AF',
  'KN': 'NA',
  'LC': 'NA',
  'MF': 'NA',
  'PM': 'NA',
  'VC': 'NA',
  'WS': 'OC',
  'SM': 'EU',
  'ST': 'AF',
  'SA': 'AS',
  'SN': 'AF',
  'RS': 'EU',
  'SC': 'AF',
  'SL': 'AF',
  'SG': 'AS',
  'SX': 'NA',
  'SK': 'EU',
  'SI': 'EU',
  'SB': 'OC',
  'SO': 'AF',
  'ZA': 'AF',
  'GS': 'AN',
  'SS': 'AF',
  'ES': 'EU',
  'LK': 'AS',
  'SD': 'AF',
  'SR': 'SA',
  'SJ': 'EU',
  'SZ': 'AF',
  'SE': 'EU',
  'CH': 'EU',
  'SY': 'AS',
  'TW': 'AS',
  'TJ': 'AS',
  'TZ': 'AF',
  'TH': 'AS',
  'TL': 'AS',
  'TG': 'AF',
  'TK': 'OC',
  'TO': 'OC',
  'TT': 'NA',
  'TN': 'AF',
  'TR': 'AS',
  'TM': 'AS',
  'TC': 'NA',
  'TV': 'OC',
  'UG': 'AF',
  'UA': 'EU',
  'AE': 'AS',
  'GB': 'EU',
  'US': 'NA',
  'UM': 'OC',
  'VI': 'NA',
  'UY': 'SA',
  'UZ': 'AS',
  'VU': 'OC',
  'VE': 'SA',
  'VN': 'AS',
  'WF': 'OC',
  'EH': 'AF',
  'YE': 'AS',
  'ZM': 'AF',
  'ZW': 'AF'
}

# -----------------------------------------------------------------------------
# functions

def genData(n,m=27):
    """Generate data for a histogram, including n counts and m entries."""
    global cntry_to_cont
    cntries = cntry_to_cont.keys()
    mx = len(cntry_to_cont)
    indices = [ random.randint(0,mx-1) for j in range(m) ]
    ix = len(indices)
    counts = {}
    for i in range(n):
        x = random.randint(0,ix-1)
        c = cntries[indices[x]]
        if c in counts:
            counts[c] += 1
        else:
            counts[c] = 1
    return counts

def show_histo(dict, orient="horiz", label="counts", title="title"):
    """Take a dictionary of counts and show it as a histogram."""
    if orient=="horiz":
        bar_fun = plt.barh     # NB: this assigns a function to bar_fun!
        bar_ticks = plt.yticks
        bar_label = plt.xlabel
    elif orient=="vert":
        bar_fun = plt.bar
        bar_ticks = plt.xticks
        bar_label = plt.ylabel
    else:
        raise Exception("show_histo: Unknown orientation: %s ".format % orient)

    n = len(dict)
    bar_fun(range(n), dict.values(), align='center', alpha=0.4)
    bar_ticks(range(n), dict.keys())  # NB: uses a higher-order function
    bar_label(label)
    plt.title(title)
    plt.show()
    
# Main:
if __name__ == '__main__':
    if len(sys.argv)<2:
        print "Usage: simple_histo <no of elems>"
        exit(1)

    num = int(sys.argv[1])
    
    # fixed input
    counts = { 'GB': 5,
               'US': 12,
               'AT': 3,
               'NZ': 7 }


    # # horizontal bars: very simple, fixed input
    plt.barh([1,2,3], [22,33,77], align='center', alpha=0.4)
    #        indices  values
    plt.show()

    # horizontal bars: very simple, fixed input; labels
    plt.barh(range(3), [22,33,77], align='center', alpha=0.4)
    plt.yticks(range(3), ["A","B","C"]) # counts.values())
    plt.xlabel('counts')
    plt.title('title')
    plt.show()

    # horizontal bars: data from counts dictionary
    n = len(counts)
    plt.barh(range(n), counts.values(), align='center', alpha=0.4)
    plt.yticks(range(n), counts.keys()) # counts.values())
    plt.xlabel('counts')
    plt.title('Number of countries represented')
    plt.show()

    # Auto-generate data, and use a function show histogram
    m = 13
    print ("Generating data with ", num, " counts for ", m, " countries")
    rand_counts = genData(num, m);
    try:
        show_histo(rand_counts,orient="vert",title="Number of countries represented, now using a function")
        show_histo(rand_counts,orient="diag",title="Number of countries represented, now using a function")
    except: 
        print ("Exception raised. Aborting.")
        exit (1)
