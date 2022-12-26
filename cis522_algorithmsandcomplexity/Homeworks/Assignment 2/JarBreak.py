def binomialcoeff(x, n, k):
    i = 1  # Index to move through n rungs
    jars = 0  # Keep track of jars being thrown
    coeff = 1  # Binomial coefficient obtained by the formula: C(n,k): jars_i_0^k {(n-i+1)/i}

    while i <= n and jars < k:
        # Argument x is the highest safe rung
        coeff *= x - i + 1
        coeff /= i
        jars += coeff
        i = i + 1
    return jars


def safestrung(n, k):
    first = 1  # First rung
    last = k  # Last rung

    # Binary Search

    while first < last:
        mid = (first + last) / 2
        if binomialcoeff(mid, n, k) < k:
            first = mid + 1  # First Jar did not break. We can go higher
        else:
            last = mid  # Jar broke. Exit loop. We have found the highest safe rung
    return int(first)
