import argparse
import os
import random
import subprocess

parser = argparse.ArgumentParser()
parser.add_argument('--jar', type=str,required=True)
parser.add_argument('--args', type=str,default='')
args = parser.parse_args()

start = (os.times())
subprocess.call('java -jar '+args.jar+' '+args.args,shell=True,stdout=open(os.devnull, 'wb'))
stop = (os.times())
print((stop[2]+stop[3])-(start[2]+start[3]))

