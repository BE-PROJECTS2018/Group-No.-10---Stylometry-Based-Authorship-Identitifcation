import pandas as pd
import gensim
from gensim.models.doc2vec import TaggedDocument
from nltk import RegexpTokenizer
from nltk.corpus import stopwords
from os import listdir
from os.path import isfile, join
import os
from nltk.tokenize import sent_tokenize, word_tokenize
import numpy as np
import pylab

def nlp_clean(data):

   new_data = []
   for d in data:
      new_str = d.lower()
      dlist = tokenizer.tokenize(new_str)
      dlist = list(set(dlist).difference(stopword_set))
    #   dlist = list(set(dlist))
      new_data.append(dlist)
   return new_data        

class LabeledLineSentence(object):

    def __init__(self, doc_list, labels_list):

        self.labels_list = labels_list
        self.doc_list = doc_list

    def __iter__(self):

        for idx, doc in enumerate(self.doc_list):
              yield gensim.models.doc2vec.LabeledSentence(doc,[self.labels_list[idx]])


path = "C:/xampp/htdocs/BE/upload_folder"
#now create a list that contains the name of all the text file in your data #folder
#create a list data that stores the content of all text files in order of their names in docLabels
data =[]
d = []
tagged_docs=[]
docLabels = []
authors = []
words=[]
vec = []
row = 0
tokenizer = RegexpTokenizer(r'\w+')
stopword_set = set(stopwords.words('english'))
turn = 0
dir_path = "C:/xampp/htdocs/BE/upload_folder"
#print(dir_path)
file_path = [x[0] for x in os.walk(dir_path)]
# #print(file_path[0:])
for each_dir in file_path[0:]:
    each_file = os.listdir(each_dir)
    print('1st for loop')
    if len(each_file) > 0:
        print('1st if')
        for file in each_file:
            # print('2nd for loop')
            full_file_path = each_dir+'/'+file

            print(full_file_path)
            file_path=full_file_path.split(os.sep)
            #file_name = file_path[]]
            file_content = open(full_file_path,'rb').read()
            print(file_content)
            data=sent_tokenize(str(file_content))
            print('data')
            data= nlp_clean(data)
            print('data')
            i=0
            del authors[:]
            del words[:]
            del   vec[:]
            d.extend(data)
            i =0
            print('Before gensim')
            model = gensim.models.Word2Vec(d, min_count=1,size=300)
            print('Before 3rd for loop')
            for word in model.wv.vocab:
                print('3rd for loop')
                vec.append( model.wv[word])
                words.append(word)
            print('3rd for loop ends')
            df1 = pd.DataFrame(np.array(vec).reshape(len(model.wv.vocab),300))
            df = pd.DataFrame(np.array(words).reshape(len(words),1))
            df = pd.concat([df,df1],axis=1)
            df.to_csv('C:/xampp/htdocs/BE/word2vec_test.csv', mode='a', header = None)
            del data[:]
            del d[:]
            # print('In process')
# print('successful')

array= df._values
from sklearn import preprocessing
from sklearn import utils
import csv
from sklearn.decomposition import PCA
import pandas as pd
# df= pd.read_csv('C:\\Users\\Shashank\\AppData\\Local\\Programs\\Python\\Python36\\Scripts\\word2vec_result1.csv')
# #df= pd.read_csv('C:\\Users\\Shashank\\AppData\\Local\\Programs\\Python\\Python36\\Scripts\\doc2vec_result2.csv')
# #df= pd.read_csv('C:\\Users\\Shashank\\AppData\\Local\\Programs\\Python\\Python36\\Scripts\\tfidf_authors.csv')
# #print(df)

X =array[:,2:300]
#X = array[:, :665]
#Y = array[:,666]

#print('Loaded Data File')
print()

import random	
import numpy as np
#from sklearn.datasets import load_NBData
#from sklearn import tree
#from sklearn import svm
from sklearn.neural_network import MLPClassifier
#from sklearn.naive_bayes import MultinomialNB

import pickle
# pickle.dump(clf,open("model.pkl", 'wb'))
clf = pickle.load(open("C:/xampp/htdocs/BE/model.pkl", 'rb'))

#Prediction
Z = clf.predict(X)
print('The prediction of the author are:')
print(Z)
Z= np.ndarray.tolist(Z)

Edmund_Goldsmid = 0
EdwardBulwerLytton = 0
JohnBurroughs = 0 
JohnGreenleafWhittier = 0
MartinAnderson = 0
#print(type(Z))
for name in Z:
    #print(name)
    if name == 'Edmund Goldsmid':
        Edmund_Goldsmid=Edmund_Goldsmid+1
    if name == 'Edward Bulwer-Lytton Henry Lewis':
        EdwardBulwerLytton+=1
    if name == 'John Burroughs':
        JohnBurroughs+=1
    if name == 'John Greenleaf Whittier':
        JohnGreenleafWhittier+=1
    if name == 'Martin Anderson':
        MartinAnderson+=1
print()
print('Edmund Goldsmid Count=')
print(Edmund_Goldsmid)
print()
print('Edward Bulwer-Lytton Henry Lewis Count=')
print(EdwardBulwerLytton)
print()
print('John Burroughs Count=')
print(JohnBurroughs)
print()
print('John Greenleaf Whittier Sue Count=')
print(JohnGreenleafWhittier)
print()
print('Martin Anderson Count=')
print(MartinAnderson)

import matplotlib.pyplot as plt
 
# Data to plot
labels = 'Edmund Goldsmid', 'Edward Bulwer-Lytton', 'John Burroughs', 'John Greenleaf Whittier', 'Martin Anderson'
sizes = [Edmund_Goldsmid, EdwardBulwerLytton, JohnBurroughs, JohnGreenleafWhittier, MartinAnderson]
colors = ['gold', 'yellowgreen', 'lightcoral', 'lightskyblue', 'red']
explode = (0, 0, 0, 0, 0)  # explode 1st slice
 
# Plot
plt.pie(sizes, explode=explode, labels=labels, colors=colors,
        autopct='%1.1f%%', shadow=True, startangle=140)
plt.axis('equal')
plt.savefig('C:/xampp/htdocs/BE/chart.png')
plt.show()
print('successful')