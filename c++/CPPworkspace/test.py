import sys
n = int(sys.argv[1]) if len(sys.argv) > 1 else 10
with open('/home/davi/CPPworkspace/hightemp.txt', encoding='utf-8') as f:
    lines = f.readlines()
chunk_size = len(lines) // n
for i in range(n):
    start = i * chunk_size
    end = start + chunk_size
    filename = f'output_{i}.txt'
    with open(filename, mode='w', encoding='utf-8') as f:
        f.writelines(lines[start:end])