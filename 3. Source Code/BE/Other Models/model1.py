import csv
import pandas as pd
import random
import numpy as np
from sklearn.decomposition import PCA
from sklearn import svm
#from sklearn.neural_network import MLPClassifier
#from sklearn import tree
from sklearn.metrics import accuracy_score

df= pd.read_csv('C:\\Users\\Admin\\Desktop\\BE Proj\\MidFrequencyTrain.txt')
print(df)
array= df._values
df_x =array[:,0:2014]
df_y =array[:,2015]

fd= pd.read_csv('C:\\Users\\Admin\\Desktop\\BE Proj\\MidFrequencyTest.txt')
print(fd)
array= fd._values
fd_x =array[:,0:2014]
fd_y =array[:,2015]


pca = PCA(n_components=350)
x = pca.fit(df_x).transform(df_x)
trans = pd.DataFrame(data = x)
trans.to_csv('C:\\Users\\Admin\\Desktop\\BE Proj\\PCACoefTrain.csv')
print()
print(x)
print()

pca1 = PCA(n_components = 350)
w = pca1.fit(fd_x).transform(fd_x)
trans1 = pd.DataFrame(data = w)
trans1.to_csv('C:\\Users\\Admin\\Desktop\\BE Proj\\PCACoefTest.csv')
print()
print(w)
print()