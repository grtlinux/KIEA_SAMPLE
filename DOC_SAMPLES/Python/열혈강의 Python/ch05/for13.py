# for13.py
N = [20, 15, 39, 2, 7, 5, 223, 75, 46, 87]
M = [3, 5, 9, 2, 7]
L = [None] * 10
for k in M:
    L[k] = k * N[k]
print L
