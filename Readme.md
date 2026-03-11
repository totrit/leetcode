# Algorithms
## Monotonic Queue / Stack
If on a micro level you can chop away a few numbers from a list, on macro level you can achieve Monotonic
E.g. `239. Sliding Window Maximum`

## Binary Index Tree (BIT)
Use `i and -i` (1-based) to Least Significant Bit (LSB), to help traverse the sum hierarchy.
E.g. To get sum of position 1~7 (index is 1-based), only need to sum up position 4 (taking care of 1~4), 6 (taking care of 5~6) and 7
O(LogN) query time to get sum between 2 indices