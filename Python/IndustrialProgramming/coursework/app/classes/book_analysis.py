import matplotlib.pyplot as plt
import pandas as pd
from classes.country_locator import CountryLocator


class BookAnalysis:

    __labels = ["ts", "visitor_uuid", "visitor_source", "visitor_device", "visitor_useragent",
                "visitor_ip", "visitor_country", "visitor_referrer", 'env_type', 'env_doc_id']

    def __init__(self, book_data):
        self.__book_data = book_data
        self.__book_data = self.__book_data[self.__labels]

    def find_by_book(self, UUID):
        """ Select Series from DataFrame by book UUID"""
        try:
            single_book = self.__book_data[self.__book_data['env_doc_id'] == UUID]
            return single_book
        except:
            raise Exception("Could not find book")

    def counries_by_book(self, UUID):
        """Get number of readers per country """
        try:
            book_data = self.find_by_book(UUID)
            by_country = book_data['visitor_country'].value_counts()
            return by_country
        except Exception, err:
            raise Exception(err)

    def counries_by_book_plot(self, by_country, UUID):
        """Create a Histogram of Countries to Popularity """
        by_country = by_country.plot(kind='bar')
        plt.savefig('static/results/countries_to_book_' + UUID + '.png')

    def continent_by_book(self, result_countries, uuid):
        by_country = result_countries.to_dict()
        by_continent = {}
        cl = CountryLocator()
        for key, value in by_country.iteritems():
            continent = cl.get_countr_continent(key)
            if continent in by_continent:
                by_continent[continent] += value
            else:
                by_continent[continent] = value
        return by_continent

    def continet_by_book_plot(self, by_continent, UUID):
        by_continent_data = pd.Series(by_continent.values(), by_continent.keys())
        by_continent_data.plot(kind='bar')
        plt.savefig('static/results/continent_to_book_' + UUID + '.png')
