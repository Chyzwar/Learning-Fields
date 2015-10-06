words = ['dupa', 'mistrze', 'wiesz']


anagrams_comp = [x[::-1] for x in words]
anagrams_lambda = map(lambda x: x[::-1], words)

print(anagrams_comp)
print(anagrams_lambda)


def reverse(text):
    if len(text) <= 1:
        return text
    return reverse(text[1:]) + text[0]


anagrams_comp_rev = [reverse(x) for x in words]
print(anagrams_comp_rev)
