import csv
import pandas as pd
import random
import numpy as np
from sklearn.decomposition import PCA
from sklearn import svm
#from sklearn.neural_network import MLPClassifier
#from sklearn import tree
from sklearn.metrics import accuracy_score

df= pd.read_csv('C:\\Users\\Admin\\Desktop\\BE Proj\\HighFrequency.txt')
print(df)
array= df._values
df_x =array[:,0:3838]
df_y =array[:,3839]


pca = PCA(n_components=1000)
x = pca.fit(df_x).transform(df_x)
trans = pd.DataFrame(data = x)
trans.to_csv('C:\\Users\\Admin\\Desktop\\BE Proj\\PCACoef.csv')
print()
print(x)
print()
MyList = np.random.randint(1700, size=340)
testing= [] 
testing = MyList

NBData_data=x
NBData_target=df_y

#Training Data
train_target = np.delete(NBData_target , testing)
train_data= np.delete(NBData_data , testing, axis=0)

#Testing Data			
test_target = NBData_target[testing]
test_data = NBData_data[testing]

#Classifier
clf = svm.SVC(kernel='poly', C=1.5, degree = 3)
#clf = MLPClassifier(hidden_layer_sizes=(32,32))
#clf = tree.DecisionTreeClassifier()

clf.fit(train_data, train_target)

#Prediction
Z = clf.predict(test_data)
print('The prediction of the authors are:')
print(Z)

#Accuracy
print()
print('Accuracy of the system:')
print(accuracy_score(test_target , Z))

#print(pca.explained_variance_ratio_)
#print(pca.singular_values_)
#print(pca.components_)
#print(pca.mean_)
