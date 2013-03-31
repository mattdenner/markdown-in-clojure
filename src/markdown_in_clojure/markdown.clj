(ns markdown-in-clojure.markdown
  (:use markdown-in-clojure.html))

(refer 'markdown-in-clojure.html :only '[element])

(defn- header
  "Processes markdown header entries"
  [markdown]
  (let [[_ indent text] (re-find (re-matcher #"^(#{1,6})\s*([^#]*[^#\s])(?:\s*#{1,6})?$" markdown))]
    (and text (element (str "h" (count indent)) text))))

(defn- horizontal-ruler
  "Processes the horizontal ruler entries"
  [markdown]
  (and (re-find (re-matcher #"^\s*(?:(?:-\s*){3,}|(?:_\s*){3,}|(?:\*\s*){3,})\s*$" markdown)) "<hr/>"))

(defn- links
  "Processes the hyperlink entries"
  [markdown]
  (let [[ _ text link title] (re-find (re-matcher #"\[([^\]]+)\]\(([^\s]+)(?:\s+([^\)]+))?\)" markdown))
        attributes           (if (nil? title) { :href link } { :href link, :title title })]
    (and text (element "a" text attributes))))

(def rules
  "Here are the rules that will applied to the markdown content"
  [
   header
   horizontal-ruler
   links
   ])
