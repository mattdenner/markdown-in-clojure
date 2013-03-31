(ns markdown-in-clojure.html)

(defn- pair-of-attributes [[key value]] (and key (str (name key) "=\"" value "\"")))

(defn- map-to-attributes
  "Give a clojure map this function returns the HTML attribute version"
  [attributes]
  (apply str (map name (flatten (interpose " " (map pair-of-attributes attributes))))))

(defn element
  "Helper function for generating valid HTML elements"
  ([name content]            (str "<" name ">" content "</" name ">"))
  ([name content attributes] (str "<" name " " (map-to-attributes attributes) ">" content "</" name ">"))
  )

(defn document
  "Given a string wraps it in a valid HTML body"
  [content]
  (element "html" (element "body" content)))

