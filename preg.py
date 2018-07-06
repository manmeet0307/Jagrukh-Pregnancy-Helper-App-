import pandas as pd
import numpy as np
from sklearn import svm
from sklearn import linear_model

data = pd.read_csv('/home/dell/Desktop/preg_data.txt',delim_whitespace=True, na_values=['NA'])
data.dropna()
print data.head()

feature_cols = ['ht', 'age', 'sga', 'parity', 'smoker']
X = data.loc[:, feature_cols]
y = data.gesage

clf = linear_model.LinearRegression()
clf.fit(X, y)
print clf.coef_
np.savetxt('coefficients.txt', clf.coef_, delimiter=',')
# coefficients are - [-0.00835546 -0.00692458 -1.52899937 -0.0301832   0.18486   ]

y_pred = clf.predict(np.asarray([164, 22, 0, 0, 2]).reshape(1, -1))
print y_pred
# predicted value for this input - [ 39.43380005] 