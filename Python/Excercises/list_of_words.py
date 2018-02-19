

def list_of_words(file_name):
    words = {}

    for line in read(file_name):
        for word in line.split():
            if word in words:
                words[word] = words[word] + 1
            else:
                words[word] = 1
