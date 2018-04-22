import csv
import pandas as pd
df= pd.read_csv('C:\\Users\\Admin\\Desktop\\BE Proj\\set2vec_result2.csv')
print(df)
array= df._values
X =array[:,0:297]
Y =array[:,298]
print(X)
print(Y)
print('Loaded Data File')

import random	
import numpy as np
#from sklearn import tree
from sklearn.neural_network import MLPClassifier
#from sklearn.svm import SVC
MyList = np.random.randint(60000, size=12000)

#random.shuffle(MyList)
#MyList = [0 , 30 , 50, 70 , 100 , 120 , 140]

testing= [] 
testing = MyList

NBData_data=X
NBData_target=Y
#Training Data
train_target = np.delete(NBData_target , testing)
train_data= np.delete(NBData_data , testing, axis=0) 

#Testing Data
test_target = NBData_target[testing]
#test_target = np.delete(NBData_target , train_target)
test_data = NBData_data[testing]
#test_data = np.delete(NBData_data , train_data, axis=0)

#Classifier
#clf = tree.DecisionTreeClassifier()
#clf = SVC()
clf = MLPClassifier(hidden_layer_sizes=(32,32))
clf.fit(train_data, train_target)


#Visualization
#tree.export_graphviz(clf, out_file='tree.pdf') 

#Prediction
#predictions = clf.predict()
Z = clf.predict(test_data)
print(Z)

#Accuracy
from sklearn.metrics import accuracy_score
print(accuracy_score(test_target , Z))
	
print('Successful')
