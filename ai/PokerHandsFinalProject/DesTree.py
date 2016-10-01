def warn(*args, **kwargs):
    pass
import warnings
warnings.warn = warn

import numpy as np
from sklearn.tree import DecisionTreeClassifier
from sklearn.ensemble import RandomForestClassifier
from sklearn.tree import export_graphviz

'''File I/O'''
def formatData(path):
	file = open(path)
	data = file.read()
	file.close()
	lines = [line.strip() for line in data.splitlines()]
	vals = [val.split(",") for val in lines]

	attributes = [[0]*10 for x in range(len(vals))]
	classifier = [0]*len(vals)
	'''Convert Data To Integers'''
	for i in range(len(vals)):
		for j in range(len(vals[0])):
			if j < 10:
				attributes[i][j] = float(vals[i][j])
			else:
				classifier[i] = int(vals[i][j])
	return[classifier, attributes]

data = formatData("poker-hand-training-true.data.txt")

attributes = np.array(data[1])
classifier = np.array(data[0])
classifier = classifier.reshape(len(classifier),1)

'''Create dot file for Tree Visualization'''
clf = DecisionTreeClassifier()
clf.fit(attributes,classifier)
with open('tree.dot', 'w') as dotfile:
    export_graphviz(clf,dotfile)


'''Okay... Well that wasnt great. Below Im modifying the to find unique ranks
	for the data set and using those 2 attr to reduce the branching factor off the tree'''

#Isloate Unique ranks and suits
ranksAttr = [hand[0::2] for hand in attributes] 
suitAttr = [hand[1::2] for hand in attributes]

#Collapse ranks and suits to cardinality of unique elements

uniRankAttr = [len(np.unique(hand)) for hand in ranksAttr]
uniSuitAttr = [len(np.unique(hand)) for hand in suitAttr]

uniquenessArr = [np.array(uniRankAttr), np.array(uniSuitAttr)]
uniquenessArr = np.array(uniquenessArr)
uniquenessArr = uniquenessArr.T
print(uniquenessArr.shape)



clfUni = DecisionTreeClassifier()
clfUni.fit(uniquenessArr, classifier)
with open('treeUni.dot', 'w') as dotfile:
    export_graphviz(clfUni,dotfile)


testData = formatData("poker-hand-testing.data.txt")

testAttributes = np.array(testData[1])
testClassifier = np.array(testData[0])
testClassifier = testClassifier.reshape(len(testClassifier),1)

testRanksAttr = [hand[0::2] for hand in testAttributes] 
testSuitAttr = [hand[1::2] for hand in testAttributes]

TuniRankAttr = [len(np.unique(hand)) for hand in testRanksAttr]
TuniSuitAttr = [len(np.unique(hand)) for hand in testSuitAttr]

TuniquenessArr = [np.array(TuniRankAttr), np.array(TuniSuitAttr)]
TuniquenessArr = np.array(TuniquenessArr)
TuniquenessArr = TuniquenessArr.T

correct = 0
incorrect = 0
uCorrect = 0
uIncorrect = 0

for x in range(len(TuniquenessArr)-2):
	tpredition = clfUni.predict(TuniquenessArr[x])
	predition = clf.predict(testAttributes[x])

	if predition == testClassifier[x]:
		correct += 1
	else:
		incorrect += 1 

	if tpredition == testClassifier[x]:
		uCorrect += 1
	else:
		uIncorrect += 1

print("With raw attributes")
print(correct)
print(incorrect)
print("With Uniques Attr")
print(uCorrect)
print(uIncorrect)

