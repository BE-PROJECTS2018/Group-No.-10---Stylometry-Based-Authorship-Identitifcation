from sklearn import preprocessing
from sklearn import utils
import csv
from sklearn.decomposition import PCA
import pandas as pd
df= pd.read_csv('C:\\Users\\Shashank\\AppData\\Local\\Programs\\Python\\Python36\\Scripts\\word2vec_result3.csv')

array= df._values
X =array[:,2:300]
Y =array[:,302]

print('data')
print(X)
print('authors')
print(Y)
print('Loaded Data File')
print()

import random	
import numpy as np
from sklearn.neural_network import MLPClassifier

clf = MLPClassifier(hidden_layer_sizes=(32,32))
clf.fit(X, Y)
print('Data Trained')

import pickle
pickle.dump(clf,open("C:\\Users\\Shashank\\AppData\\Local\\Programs\\Python\\Python36\\Scripts\\model.pkl", 'wb'))

print('Successful')