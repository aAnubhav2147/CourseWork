from JarBreak import safestrung as safest_rung

print("Input the maximum number of floors")
floors = input()
n = int(floors)

print("Input the maximum number of jars")
jars = input()
k = int(jars)

print("The number of trials required for finding the highest safest rung for", k,
      "jars to be dropped from a ladder of", n,
      "rungs is ", safest_rung(n, k))
