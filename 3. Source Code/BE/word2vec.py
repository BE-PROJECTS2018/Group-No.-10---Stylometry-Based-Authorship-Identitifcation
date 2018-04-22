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


def nlp_clean(data):

   new_data = []
   for d in data:
      new_str = d.lower()
      dlist = tokenizer.tokenize(new_str)
      dlist = list(set(dlist).difference(stopword_set))
      new_data.append(dlist)
   return new_data        

class LabeledLineSentence(object):

    def __init__(self, doc_list, labels_list):

        self.labels_list = labels_list
        self.doc_list = doc_list

    def __iter__(self):

        for idx, doc in enumerate(self.doc_list):
              yield gensim.models.doc2vec.LabeledSentence(doc,[self.labels_list[idx]])


path = "/home/mahak/Documents/testData1"
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
dir_path = "/home/mahak/Documents/trainingdata1"
file_path = [x[0] for x in os.walk(dir_path)]
for each_dir in file_path[1:]:
    each_file = os.listdir(each_dir)
    if len(each_file) > 0:
        for file in each_file:
            full_file_path = each_dir+'/'+file
            print(full_file_path)
	    file_path=full_file_path.split(os.sep)
	    file_name = file_path[6]
            file_content = open(full_file_path,'rb').read()
            data=sent_tokenize(file_content)
            #print(data)
            data= nlp_clean(data)
	    #print(len(data))
            #iterator returned over all documents
            #it = LabeledLineSentence(data,)
	    #print(len(data))
	    i=0
	    
	    del authors[:]
	    del words[:]
	    del   vec[:]
	    d.extend(data)
	  
	    i =0
	    model = gensim.models.Word2Vec(d, min_count=1,size=300)
	    for word in model.wv.vocab:
		    vec.append( model.wv[word])
		    words.append(word)
		    authors.append(file_path[5])
	    df1 = pd.DataFrame(np.array(vec).reshape(len(model.wv.vocab),300))
	    df2 = pd.DataFrame(np.array(authors).reshape(len(authors),1))
	    df = pd.DataFrame(np.array(words).reshape(len(words),1))
	    df = pd.concat([df,df1,df2],axis=1)
	    df.to_csv("word2vec_result3.csv",mode='a',header = None)
	    del data[:]
	    del d[:]
'''
#print(d)
#print(type(d))
print(len(d))
print(len(docLabels))
print(docLabels)

'''

	    
#it = LabeledLineSentence(d,docLabels)            
#model = gensim.models.Doc2Vec(vector_size=300, min_count=0, alpha=0.025, min_alpha=0.025)

'''
model.build_vocab(it)
    
            #training of model
for epoch in range(5):
	print('iteration')
	print(str(epoch+1))
	model.train(it,total_examples=model.corpus_count, epochs=model.epochs)
	model.alpha -= 0.002
	model.min_alpha = model.alpha
            #saving model 
model.save('doc2vec_train.model')
print('model saved')

       #loading the model
d2v_model = gensim.models.doc2vec.Doc2Vec.load('doc2vec_train.model')
#print(type(d2v_model))
#print(d2v_model.docvecs[0])
#print(len(d2v_model.docvecs))
       #start testing
       #printing the vector of document at index 1 in docLabels
#docvec = d2v_model.docvecs[2565]
#print(docvec)
'''
#print(model.wv['insipid'])
#print(type(vec))

#print(type(v))

#frame = pd.Dataframe(data = vec)