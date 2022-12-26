#!/usr/bin/env python
# coding: utf-8

# In[1]:


# Load required packages

import numpy as np
import matplotlib.pyplot as plt


# In[2]:


# Read in the .dat file
data = np.loadtxt("trapdata.dat")

# Create two atomic vectors/variables to store the dat file contents
x = data[:,0]
y = data[:,1]

# Let's take the logarithms of the above vectors to create a log-log plot correspondingly
xlog = np.log(x)
ylog = np.log(y)


# In[3]:


# Create a log-log plot

#plt.scatter(xlog, ylog, color='purple') # Plot a scatter plot
plt.plot(xlog, ylog, color='purple') # Plot a solid line
plt.xlabel('Log(N)') # Label the x-axis
plt.ylabel('Log(Error)') # Label the y-axis
plt.title('N vs. Error Log Plot') # Title of the graph
# Save the plot as a PNG file

plt.savefig("NvError.png")


# In[9]:





# In[ ]:




