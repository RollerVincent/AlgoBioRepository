import argparse
import os
import random

parser = argparse.ArgumentParser()
parser.add_argument('--out', type=str,required=True)
parser.add_argument('--start', type=int,default=1)
parser.add_argument('--range', type=int,required=True)
parser.add_argument('--step', type=int,required=True)

args = parser.parse_args()

if args.step<1:
    args.step=1

if not os.path.exists(args.out):
    os.makedirs(args.out)

seq = [random.randint(-9, 9) for i in range(args.start)]

for i in range(args.range+1):
    n = args.start + i*args.step
    for j in range (n-len(seq)):
        seq.append(random.randint(-9, 9))
    with open(args.out+'/N_'+str(n)+'.txt','w') as f:
        f.write('# n = '+str(n)+'\n')
        for s in seq:
            f.write(str(s)+'\t')

