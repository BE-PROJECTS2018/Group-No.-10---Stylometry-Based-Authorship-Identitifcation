import gensim
from gensim.models.doc2vec import TaggedDocument
from nltk import RegexpTokenizer
from nltk.corpus import stopwords
from os import listdir
from os.path import isfile, join
import os
from nltk.tokenize import sent_tokenize, word_tokenize


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
data=[]
tagged_docs=[]
docLabels = []
tokenizer = RegexpTokenizer(r'\w+')
stopword_set = set(stopwords.words('english'))

dir_path = "C:\\Users\\Admin\\Desktop\\BE Proj\\218"
file_path = [x[0] for x in os.walk(dir_path)]
for each_dir in file_path[1:]:
    each_file = os.listdir(each_dir)
    if len(each_file) > 0:
        for file in each_file:
            full_file_path = each_dir+'/'+file
            print(full_file_path)
            file_content = open(full_file_path,'rb').read()
            data = sent_tokenize(file_content)
            print(len(data))
                #data.append(file_content)
            data = nlp_clean(data)
        print(len(data))
            #iterator returned over all documents
            #it = LabeledLineSentence(data,)
        #print(len(data))
        i=0
        for line in data:
            tg = 'for_'+str(i)
            i+=1
            docLabels.append(tg)
            #print(tg)
            #tagged_docs.append(TaggedDocument(words=line.lower().split(),tags=[tg]))
            it = LabeledLineSentence(data,docLabels)
            model = gensim.models.Doc2Vec(vector_size=300, min_count=0, alpha=0.025, min_alpha=0.025)
            model.build_vocab(it)
    
            #training of model
            for epoch in range(2):
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
#print(type(d2v_model.docvecs[0]))
print(len(d2v_model.docvecs))
       #start testing
       #printing the vector of document at index 1 in docLabels
#docvec = d2v_model.docvecs['for_2000']
#print(docvec)