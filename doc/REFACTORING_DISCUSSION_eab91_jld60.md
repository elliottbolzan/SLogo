Refactoring Discussion
==========

**Authors**: Elliott Bolzan (eab91) and Jay Doherty (jld60).

In this lab, we went through all of our classes and removed extraneous public methods. 

We started out with around 550 lines of public methods, and removed around 70% of the references. The vast majority of these references could be made `protected`, as they were all subclasses of one abstract class – `Command`.

---
In addition, we removed any unecessary concrete class we found. We replaced those with higher level classes, like `List` and `Map`. It was time well spent!