import urllib
import numpy as np
from sklearn.discriminant_analysis import LinearDiscriminantAnalysis, QuadraticDiscriminantAnalysis
import matplotlib.pyplot as plt
from sklearn.preprocessing import label_binarize
from sklearn.multiclass import OneVsRestClassifier
from sklearn.cross_validation import train_test_split
from sklearn.metrics import roc_curve, auc

link = "http://archive.ics.uci.edu/ml/machine-learning-databases/poker/poker-hand-training-true.data"
import urllib.request
with urllib.request.urlopen(link) as url:
	data = url.read()

dataString = str(data)
dataLines = dataString.split("\\r\\n")
dataLines = dataLines[1:-1]

Xlist = []
ylist = []

for line in dataLines:
	nums = line.split(",")
	ylist.append(nums[-1])
	del nums[-1]
	Xlist.append(list(map(int, nums)))

ylist = list(map(int, ylist))

X = np.asarray(Xlist)
y = z = np.asarray(ylist)

clf = LinearDiscriminantAnalysis()
clf.fit(X,y)

p0 = p1 = p2 = p3 = p4 = p5 = p6 = p7 = p8 = p9 = 0
a0 = a1 = a2 = a3 = a4 = a5 = a6 = a7 = a8 = a9 = 0
for a in range(0, len(X)):
	z = clf.predict([X[a]])
	if(z == 0):
		p0 += 1
		if(y[a] == 0):
			a0 += 1
	elif(z == 1):
		p1 += 1
		if(y[a] == 1):
			a1 += 1
	elif(z == 2):
		p2 += 1
		if(y[a] == 2):
			a2 += 1
	elif(z == 3):
		p3 += 1
		if(y[a] == 3):
			a3 += 1
	elif(z == 4):
		p4 += 1
		if(y[a] == 4):
			a4 += 1
	elif(z == 5):
		p5 += 1
		if(y[a] == 5):
			a5 += 1
	elif(z == 6):
		p6 += 1
		if(y[a] == 6):
			a6 += 1
	elif(z == 7):
		p7 += 1
		if(y[a] == 7):
			a7 += 1
	elif(z == 8):
		p8 += 1
		if(y[a] == 8):
			a8 += 1
	else:
		p9 += 1
		if(y[a] == 9):
			a9 += 1

c0 = c1 = c2 = c3 = c4 = c5 = c6 = c7 = c8 = c9 = 0
for b in range(0, len(y)):
	if(y[b] == 0):
		c0 += 1
	if(y[b] == 1):
		c1 += 1
	if(y[b] == 2):
		c2 += 1
	if(y[b] == 3):
		c3 += 1
	if(y[b] == 4):
		c4 += 1
	if(y[b] == 5):
		c5 += 1
	if(y[b] == 6):
		c6 += 1
	if(y[b] == 7):
		c7 += 1
	if(y[b] == 8):
		c8 += 1
	if(y[b] == 9):
		c9 += 1

print('For linear discriminant analysis:')
print('Class 0: ', p0, 'predicted, of which', a0, 'are true positives out of', c0, 'true total')
print('Class 1: ', p1, 'predicted, of which', a1, 'are true positives out of', c1, 'true total')
print('Class 2: ', p2, 'predicted, of which', a2, 'are true positives out of', c2, 'true total')
print('Class 3: ', p3, 'predicted, of which', a3, 'are true positives out of', c3, 'true total')
print('Class 4: ', p4, 'predicted, of which', a4, 'are true positives out of', c4, 'true total')
print('Class 5: ', p5, 'predicted, of which', a5, 'are true positives out of', c5, 'true total')
print('Class 6: ', p6, 'predicted, of which', a6, 'are true positives out of', c6, 'true total')
print('Class 7: ', p7, 'predicted, of which', a7, 'are true positives out of', c7, 'true total')
print('Class 8: ', p8, 'predicted, of which', a8, 'are true positives out of', c8, 'true total')
print('Class 9: ', p9, 'predicted, of which', a9, 'are true positives out of', c9, 'true total')

clf = QuadraticDiscriminantAnalysis()
clf.fit(X,y)

p0 = p1 = p2 = p3 = p4 = p5 = p6 = p7 = p8 = p9 = 0
a0 = a1 = a2 = a3 = a4 = a5 = a6 = a7 = a8 = a9 = 0
for a in range(0, len(X)):
	z = clf.predict([X[a]])
	if(z == 0):
		p0 += 1
		if(y[a] == 0):
			a0 += 1
	elif(z == 1):
		p1 += 1
		if(y[a] == 1):
			a1 += 1
	elif(z == 2):
		p2 += 1
		if(y[a] == 2):
			a2 += 1
	elif(z == 3):
		p3 += 1
		if(y[a] == 3):
			a3 += 1
	elif(z == 4):
		p4 += 1
		if(y[a] == 4):
			a4 += 1
	elif(z == 5):
		p5 += 1
		if(y[a] == 5):
			a5 += 1
	elif(z == 6):
		p6 += 1
		if(y[a] == 6):
			a6 += 1
	elif(z == 7):
		p7 += 1
		if(y[a] == 7):
			a7 += 1
	elif(z == 8):
		p8 += 1
		if(y[a] == 8):
			a8 += 1
	else:
		p9 += 1
		if(y[a] == 9):
			a9 += 1

c0 = c1 = c2 = c3 = c4 = c5 = c6 = c7 = c8 = c9 = 0
for b in range(0, len(y)):
	if(y[b] == 0):
		c0 += 1
	if(y[b] == 1):
		c1 += 1
	if(y[b] == 2):
		c2 += 1
	if(y[b] == 3):
		c3 += 1
	if(y[b] == 4):
		c4 += 1
	if(y[b] == 5):
		c5 += 1
	if(y[b] == 6):
		c6 += 1
	if(y[b] == 7):
		c7 += 1
	if(y[b] == 8):
		c8 += 1
	if(y[b] == 9):
		c9 += 1

print('For quadratic discriminant analysis:')
print('Class 0: ', p0, 'predicted, of which', a0, 'are true positives out of', c0, 'true total')
print('Class 1: ', p1, 'predicted, of which', a1, 'are true positives out of', c1, 'true total')
print('Class 2: ', p2, 'predicted, of which', a2, 'are true positives out of', c2, 'true total')
print('Class 3: ', p3, 'predicted, of which', a3, 'are true positives out of', c3, 'true total')
print('Class 4: ', p4, 'predicted, of which', a4, 'are true positives out of', c4, 'true total')
print('Class 5: ', p5, 'predicted, of which', a5, 'are true positives out of', c5, 'true total')
print('Class 6: ', p6, 'predicted, of which', a6, 'are true positives out of', c6, 'true total')
print('Class 7: ', p7, 'predicted, of which', a7, 'are true positives out of', c7, 'true total')
print('Class 8: ', p8, 'predicted, of which', a8, 'are true positives out of', c8, 'true total')
print('Class 9: ', p9, 'predicted, of which', a9, 'are true positives out of', c9, 'true total')

y = label_binarize(y, classes = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9])
n_classes = y.shape[1]

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=.5, random_state=0)
classifier = OneVsRestClassifier(LinearDiscriminantAnalysis())
y_score = classifier.fit(X_train, y_train).decision_function(X_test)

fpr = dict()
tpr = dict()
roc_auc = dict()
for i in range(n_classes):
    fpr[i], tpr[i], _ = roc_curve(y_test[:, i], y_score[:, i])
    roc_auc[i] = auc(fpr[i], tpr[i])

fpr["micro"], tpr["micro"], _ = roc_curve(y_test.ravel(), y_score.ravel())
roc_auc["micro"] = auc(fpr["micro"], tpr["micro"])

plt.figure()
plt.plot(fpr["micro"], tpr["micro"],
         label='Micro-average ROC curve (area = {0:0.2f})'
               ''.format(roc_auc["micro"]))
for i in range(n_classes):
    plt.plot(fpr[i], tpr[i], label='ROC curve of class {0} (area = {1:0.2f})'
                                   ''.format(i, roc_auc[i]))

plt.plot([0, 1], [0, 1], 'k--')
plt.xlim([0.0, 1.0])
plt.ylim([0.0, 1.05])
plt.xlabel('False Positive Rate')
plt.ylabel('True Positive Rate')
plt.title('Multi-class ROC Curve')
plt.legend(loc="lower right")
plt.show()