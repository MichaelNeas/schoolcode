
import pandas as pd
import urllib2
import time
import operator

#-----------------DATA SETUP-----------------#

webData = urllib2.urlopen('https://archive.ics.uci.edu/ml/machine-learning-databases/poker/poker-hand-training-true.data')

#Create header
header = []
sCounter = 0
rCounter = 0
for x in range(10):
   if x%2==0:
	sCounter += 1
	header.append(str(sCounter)+"_Suit")
   else:
	rCounter += 1
	header.append(str(rCounter)+"_Rank")
header.append("Play")

#training data
trainingDF = pd.read_csv(webData,header=None)
trainingDF.columns = header
webData.close()


#Sort pairs by ranking
for i in range(len(trainingDF.index)):
	tempList = trainingDF.loc[i].tolist()
	tempDict = {"A-"+str(tempList[0]):tempList[1],"B-"+str(tempList[2]):tempList[3],"C-"+str(tempList[4]):tempList[5],"D-"+str(tempList[6]):tempList[7],"E-"+str(tempList[8]):tempList[9]}
	sortedDict = sorted(tempDict.items(),key=operator.itemgetter(1))
	trainingDF.loc[i]["1_Suit"] = sortedDict[0][0].split("-")[1]
	trainingDF.loc[i]["1_Rank"] = sortedDict[0][1]
	trainingDF.loc[i]["2_Suit"] = sortedDict[1][0].split("-")[1]
	trainingDF.loc[i]["2_Rank"] = sortedDict[1][1]
	trainingDF.loc[i]["3_Suit"] = sortedDict[2][0].split("-")[1]
	trainingDF.loc[i]["3_Rank"] = sortedDict[2][1]
	trainingDF.loc[i]["4_Suit"] = sortedDict[3][0].split("-")[1]
	trainingDF.loc[i]["4_Rank"] = sortedDict[3][1]
	trainingDF.loc[i]["5_Suit"] = sortedDict[4][0].split("-")[1]
	trainingDF.loc[i]["5_Rank"] = sortedDict[4][1]


trainingNpArray = trainingDF.as_matrix(columns=None)

#testing data
webData = urllib2.urlopen('https://archive.ics.uci.edu/ml/machine-learning-databases/poker/poker-hand-testing.data')
testingDF = pd.read_csv(webData,header=None,nrows=100000) #nrows allows you to select a n number of rows from data. Useful for a million rows.
testingDF.columns = header
webData.close()

for i in range(len(testingDF.index)):
	tempList = testingDF.loc[i].tolist()
	tempDict = {"A-"+str(tempList[0]):tempList[1],"B-"+str(tempList[2]):tempList[3],"C-"+str(tempList[4]):tempList[5],"D-"+str(tempList[6]):tempList[7],"E-"+str(tempList[8]):tempList[9]}
        sortedDict = sorted(tempDict.items(),key=operator.itemgetter(1))
        testingDF.loc[i]["1_Suit"] = sortedDict[0][0].split("-")[1]
        testingDF.loc[i]["1_Rank"] = sortedDict[0][1]
        testingDF.loc[i]["2_Suit"] = sortedDict[1][0].split("-")[1]
        testingDF.loc[i]["2_Rank"] = sortedDict[1][1]
        testingDF.loc[i]["3_Suit"] = sortedDict[2][0].split("-")[1]
        testingDF.loc[i]["3_Rank"] = sortedDict[2][1]
        testingDF.loc[i]["4_Suit"] = sortedDict[3][0].split("-")[1]
        testingDF.loc[i]["4_Rank"] = sortedDict[3][1]
        testingDF.loc[i]["5_Suit"] = sortedDict[4][0].split("-")[1]
        testingDF.loc[i]["5_Rank"] = sortedDict[4][1]
	
testingNpArray = testingDF.as_matrix(columns=None)


#--------------------BEGIN CLASSIFICATION PORTION-----------------------#

import numpy as np
from sklearn.metrics import metrics, accuracy_score
from sklearn import svm

trainInput = trainingNpArray[:,0:10]
trainOutput = trainingNpArray[:,10]

testInput = testingNpArray[:,0:10]
testOutput = testingNpArray[:,10]

#Start classification methods

print "clf: Linear SVM with C value = 1"
t1 = time.time()
clf = svm.SVC(kernel='linear', C=1)
print "training... "
clf.fit(trainInput,trainOutput)
t2 = time.time()
print 'Training: %f s used.' % (t2-t1)


print "Predicting"
t1 = time.time()
predicted = clf.predict(testInput)
t2 = time.time()
print 'Testing: %f s used.' % (t2-t1)
acc = accuracy_score(testOutput, predicted)
print "accuracy = ", acc

#fpr,tpr,thresholds = metrics.roc_curve(testOutput,predicted,pos_label=3)#If you want to increase the pos label you need to increase the number of lines are read.
#auc = metrics.auc(fpr,tpr)

print '----------------------------------'

print "clf: Radial Basis Function SVM with C value = 1"
t1 = time.time()
clf = svm.SVC(kernel='rbf', C=1)
print "training... "
clf.fit(trainInput,trainOutput)
t2 = time.time()
print 'Training: %f s used.' % (t2-t1)


print "Predicting"
t1 = time.time()
predicted = clf.predict(testInput)
t2 = time.time()
print 'Testing: %f s used.' % (t2-t1)
acc = accuracy_score(testOutput, predicted)
print "accuracy = ", acc

print '----------------------------------'

print "clf: Linear SVM with C value = .001"
t1 = time.time()
clf = svm.SVC(kernel='linear', C=.001)
print "training... "
clf.fit(trainInput,trainOutput)
t2 = time.time()
print 'Training: %f s used.' % (t2-t1)


print "Predicting"
t1 = time.time()
predicted = clf.predict(testInput)
t2 = time.time()
print 'Testing: %f s used.' % (t2-t1)
acc = accuracy_score(testOutput, predicted)
print "accuracy = ", acc

print '----------------------------------'

print "clf: Radial Basis Function SVM with C value = .001"
t1 = time.time()
clf = svm.SVC(kernel='rbf', C=.001)
print "training... "
clf.fit(trainInput,trainOutput)
t2 = time.time()
print 'Training: %f s used.' % (t2-t1)


print "Predicting"
t1 = time.time()
predicted = clf.predict(testInput)
t2 = time.time()
print 'Testing: %f s used.' % (t2-t1)
acc = accuracy_score(testOutput, predicted)
print "accuracy = ", acc

print '----------------------------------'

print "clf: Linear SVM with C value = 10"
t1 = time.time()
clf = svm.SVC(kernel='linear', C=10)
print "training... "
clf.fit(trainInput,trainOutput)
t2 = time.time()
print 'Training: %f s used.' % (t2-t1)


print "Predicting"
t1 = time.time()
predicted = clf.predict(testInput)
t2 = time.time()
print 'Testing: %f s used.' % (t2-t1)
acc = accuracy_score(testOutput, predicted)
print "accuracy = ", acc

print '----------------------------------'

print "clf: Radial Basis Function SVM with C value = 10"
t1 = time.time()
clf = svm.SVC(kernel='rbf', C=10)
print "training... "
clf.fit(trainInput,trainOutput)
t2 = time.time()
print 'Training: %f s used.' % (t2-t1)


print "Predicting"
t1 = time.time()
predicted = clf.predict(testInput)
t2 = time.time()
print 'Testing: %f s used.' % (t2-t1)
acc = accuracy_score(testOutput, predicted)
print "accuracy = ", acc

print 'Done'
