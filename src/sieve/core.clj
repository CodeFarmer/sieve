(ns sieve.core)


(defn primes-
  "This is simple and easy to understand, but it will fairly quickly
  blow the stack with nested calls to (filter) - about 1800 primes on
  my machine"
  ([]
    (primes- (iterate inc 2)))
  ([[n & rest]]
   (lazy-seq (cons n (primes- (remove #(= 0 (mod % n)) rest))))))
  

(defn primes
  "This is slightly uglier as it has to pass the list of already-seen
  primes, but it avoids piling up nested removes on the result and can
  thus go orders of magnitude further, limited to being able to keep
  all the returned values in a vector."
  ([]
   (primes [] (iterate inc 2)))
  ([so-far [n & rest]]
   (lazy-seq
    (if (some #(= 0 (mod n %)) so-far)
      (primes so-far rest)
      (cons n (primes (conj so-far n) rest))))))
