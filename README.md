# Destructive_Wrapping

## Description
In the world of software development, bug detection is a very important factor as it helps the developers to find, analyse their mistakes and debug them. Bugs can be detected in 2 ways, static and dynamic bug detection. The main difference between static and dynamic bug detection is static bug detection doesn’t need to run the code where as in dynamic bug detection the code need to be run and bug detection will take place at runtime. Static bug detection can be done in any IDE like eclipse and Intellij. I implemented a tool which detects the occurences of destructive wrapping anti pattern and give the details for the exception which makes it easier for the developers to locate where the exceptions are taking place.

### Anti-Pattern Description

#### Destructive Wrapping

Destructive wrapping def
```
catch (NoSuchMethodException e) {
throw new MyServiceException(“Blah: ” + e.getMessage());
}
```
