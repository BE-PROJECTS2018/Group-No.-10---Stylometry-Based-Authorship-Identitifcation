import csv
import pandas as pd
df= pd.read_csv('C:\\Users\\Admin\\Desktop\\BE Proj\\HighFrequency.txt')
print(df)
array= df._values
X =array[:,0:3838]
Y =array[:,3839]
#print(X)
#print(Y)
print('Loaded Data File')
print()

import random	
import numpy as np
from sklearn import svm

MyList = np.random.randint(1700, size=340)

testing= [] 
testing = MyList

NBData_data=X
NBData_target=Y
#Training Data
train_target = np.delete(NBData_target , testing)
train_data= np.delete(NBData_data , testing, axis=0) 

#Testing Data			
test_target = NBData_target[testing]
test_data = NBData_data[testing]

#Classifier
clf = svm.SVC(kernel='poly', C=1.5, degree = 3)
clf.fit(train_data, train_target)

#Visualization



#Prediction
Z = clf.predict(test_data)
print('The prediction of the authors are:')
print(Z)

#Accuracy
from sklearn.metrics import accuracy_score
print()
print('Accuracy of the system:')
print(accuracy_score(test_target , Z))
	
print('Successful')