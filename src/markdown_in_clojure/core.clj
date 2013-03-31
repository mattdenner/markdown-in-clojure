(ns markdown-in-clojure.core (:gen-class)
  (:use markdown-in-clojure.markdown)
  (:use markdown-in-clojure.html))

(defn- apply-rules-to
  "Applies rules for converting Markdown to HTML"
  [line-of-markdown]
  (some #(% line-of-markdown) markdown-in-clojure.markdown/rules))

(defn parse
  "Given a string containing Markdown, this function returns the appropriate HTML"
  [markdown]
  (markdown-in-clojure.html/document (apply str (map apply-rules-to (clojure.string/split-lines markdown)))))
