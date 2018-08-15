
def search(lib, keyword):
    assert isinstance(lib, list)
    assert isinstance(keyword, str)
    def calc(src, m):
        srcLen, mLen = len(src), len(m)
        limit = srcLen if srcLen < mLen else mLen
        recordLen = max(srcLen, mLen)
        record = [1 for _ in range(recordLen)]
        for i in range(limit):
            record[i] = (0 if src[i] == m[i] else 1)
        halfConSize = int(recordLen / 6)
        if halfConSize > 10: halfConSize = 10
        # collect
        sum = 0
        for i in range(recordLen):
            tmp = 0
            for j in range(i - halfConSize, i + halfConSize + 1):
                tmp += round(record[j] * (1 - abs(i - j) / 10.0), 4) if -1 < j < recordLen else 0
            sum += tmp
        return sum / recordLen
    value = None
    best = None
    for src in lib:
        tmp = calc(src, keyword)
        if not value or value > tmp:
            value = tmp
            best = src
    print(value, best)
lib = [
    'home depot',
    'hotmail',
    'hulu',
    'harbor freight',
    'honda',
    'hsn',
    'hotwire',
    'homeaway'
]
search(lib, 'hodwi')