from sklearn import preprocessing
from sklearn import utils
import csv
import pandas as pd
df= pd.read_csv('C:\\Users\\Admin\\Desktop\\BE Proj\\tfidf_authors.csv')
#df= pd.read_csv('C:\\Users\\Shashank\\AppData\\Local\\Programs\\Python\\Python36\\Scripts\\tfidf_authors.csv')
#print(df)
array= df._values
X = array[:, :500]
Y = array[:,501]
print('data')
print(X)
print('authors')
print(Y)
print('Loaded Data File')
print()

import random	
import numpy as np
#from sklearn.datasets import load_NBData
#from sklearn import tree
from sklearn import svm
#from sklearn.neural_network import MLPClassifier
#from sklearn.naive_bayes import MultinomialNB

MyList = np.random.randint(550, size=110)

#random.shuffle(MyList)
#MyList = [10 , 30 , 50, 70 , 100 , 120 , 140, 200, 280, 370, 500, 617, 723]

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

print(utils.multiclass.type_of_target(train_data))
print(utils.multiclass.type_of_target(train_target))

#Classifier
#clf = tree.DecisionTreeClassifier()
clf = svm.SVC(kernel='poly', C=1.5, degree = 3)
#clf = MLPClassifier(hidden_layer_sizes=(32,32))
clf.fit(train_data, train_target)

#Visualization
#tree.export_graphviz(clf, out_file='tree.dot') 

"""
dot_data = tree.export_graphviz(clf, out_file='sai.pdf', 
                         feature_names=X,  
                         class_names=Y,  
                         filled=True, rounded=True,  
                         special_characters=True
						)
graph = pydotplus.graph_from_dot_data(dot_data.getvalue())
graph.write_pdf("SAI.pdf")


from sklearn.externals.six import StringIO  
from IPython.display import Image  
from sklearn.tree import export_graphviz
import pydotplus
dot_data = StringIO()
export_graphviz(tree, out_file=dot_data,  
                filled=True, rounded=True,
                special_characters=True)
graph = pydotplus.graph_from_dot_data(dot_data.getvalue())  
Image(graph.create_png())

from IPython.display import SVG
from graphviz import Source
graph = Source( tree.export_graphviz(clf, out_file=None, feature_names=X))
png_bytes = graph.pipe(format='png')
with open('dtree_pipe.png','wb') as f:
    f.write(png_bytes)
from IPython.display import Image
Image(png_bytes)
"""

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