import csv
import pandas as pd
import random
import numpy as np
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn import svm
import os
import string
import math
import nltk
import csv
from sklearn.decomposition import PCA
from sklearn.metrics import accuracy_score
from nltk.stem import PorterStemmer
from nltk.tokenize import sent_tokenize, word_tokenize
from nltk import pos_tag
#nltk.download()


tokenize = lambda doc: doc.lower().split(" ")
all_documents=[]
str2=""
ps = PorterStemmer()
dir_path = "C:\\Users\\Shashank\\AppData\\Local\\Programs\\Python\\Python36\\Scripts\\BE Project\\traindata"
#dir_path = "C:\\Users\\Shashank\\AppData\\Local\\Programs\\Python\\Python36\\Scripts\\traindata1"
file_path = [x[0] for x in os.walk(dir_path)]
for each_dir in file_path[1:]:
    each_file = os.listdir(each_dir)
    if len(each_file) > 0:
        for file in each_file:
            full_file_path = each_dir+'\\'+file
            print(full_file_path)
            #file_content = open(full_file_path,'rb').read()
            with open(full_file_path) as f:
                temp = f.read()
            words = word_tokenize(temp) 
            for w in words:
                stemmedWords = ps.stem(w)
                #POSed=pos_tag(stemmedWords)
                str2+=stemmedWords
                str2+=" "
            print(str2)
            all_documents.append(str2)
            str2=""
            
path = "C:\\Users\\Shashank\\AppData\\Local\\Programs\\Python\\Python36\\Scripts\\BE Project\\list.csv"
with open(path) as f:
    s = f.read()

  
mylist=[]   
mylist= s.split(",")
mylist[364] = '_ye'

Extraction = pd.DataFrame(data=all_documents)
Extraction.to_csv('C:\\Users\\Shashank\\AppData\\Local\\Programs\\Python\\Python36\\Scripts\\BE Project\\Extracted Features.csv')
sklearn_tfidf = TfidfVectorizer(min_df=1, token_pattern=u'(?ui)\\b\\w*[a-z]+\\w*\\b', stop_words=mylist, sublinear_tf= True, preprocessor = callable)
sklearn_representation = sklearn_tfidf.fit_transform(all_documents)

df1 = pd.DataFrame(sklearn_representation.toarray(), columns=sklearn_tfidf.get_feature_names())
#print(df1)


trans=pd.DataFrame(data=df1)
trans.to_csv('C:\\Users\\Shashank\\AppData\\Local\\Programs\\Python\\Python36\\Scripts\\result_tfidf.csv')


#np.savetxt('tfidf.csv' , df1, delimiter=',')


"""
df= pd.read_csv('C:\\Users\\Shashank\\AppData\\Local\\Programs\\Python\\Python36\\Scripts\\MidFrequencyTrain.txt')
#print(df)

array= df._values
df_y =array[:,2015]
"""

df_x= trans
pca = PCA(n_components=500)
x = pca.fit(df_x).transform(df_x)
feat=pca.components_
print(feat)
trans1 = pd.DataFrame(data = x)
#trans = pd.DataFrame.add( other, axis = 'columns' , df_y)



authors=[]
tokenize = lambda doc: doc.lower().split(" ")
all_documents=[]
dir_path = "C:\\Users\\Shashank\\AppData\\Local\\Programs\\Python\\Python36\\Scripts\\BE Project\\trainData"
#dir_path = "C:\\Users\\Shashank\\AppData\\Local\\Programs\\Python\\Python36\\Scripts\\traindata1"
file_path = [x[0] for x in os.walk(dir_path)]
for each_dir in file_path[1:]:
    each_file = os.listdir(each_dir)
    if len(each_file) > 0:
        for file in each_file:
            full_file_path = each_dir+'\\'+file
            #print(full_file_path)
            path=full_file_path.split(os.sep)
            print(path[11])
            authors.append(path[11])

print(len(authors))


authordf = pd.DataFrame(data=authors)
#trans1 = pd.DataFrame.add(trans , axis = 'columns')
frames = [trans1, authordf]
result = pd.concat(frames , axis = 'columns')
result.to_csv('C:\\Users\\Shashank\\AppData\\Local\\Programs\\Python\\Python36\\Scripts\\tfidf_authors.csv')
print(result)
#print(result)
#print(x)