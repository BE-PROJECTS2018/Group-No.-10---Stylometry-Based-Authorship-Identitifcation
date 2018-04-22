import csv
import numpy as np
import pandas as pd
df= pd.read_csv('C:\\Users\\Admin\\Desktop\\BE Proj\\HighFrequency.txt')
print(df)
array= df._values
X =array[:,0:3838]
Y =array[:,3839]
print('Loaded Data File')
print()

#Splitting the dataset into the Training set and Test set
from sklearn.model_selection import train_test_split
X_train, X_test, y_train, y_test = train_test_split(X,Y,test_size=0.2,random_state=0)

#Fitting kernals to Training set

from sklearn.svm import SVC
#from sklearn.neural_network import MLPClassifier
#from sklearn import tree

clf = SVC(kernel='poly', C=1.5, degree = 3)
#clf = MLPClassifier(hidden_layer_sizes=(32,32))
#clf = tree.DecisionTreeClassifier()

clf.fit(X_train, y_train)

#Prediction
Z = clf.predict(X_test)
#print('The prediction of the authors are:')
#print(Z)

from sklearn.model_selection import cross_val_score
accuracies = cross_val_score(estimator=clf, X=X_train, y=y_train , cv =10)
print(accuracies)
print(accuracies.mean())
print(accuracies.std())