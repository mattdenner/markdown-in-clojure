(ns markdown-in-clojure.core-test
  (:use midje.sweet)
  (:use [markdown-in-clojure.core])
  )

(facts "About 'parser'"
       (fact "converts blank markdown into empty html"
             (parse "") => "<html><body></body></html>")

       (fact "handles header markers"
             (parse "# H1")             => "<html><body><h1>H1</h1></body></html>"
             (parse "## H2")            => "<html><body><h2>H2</h2></body></html>"
             (parse "### H3")           => "<html><body><h3>H3</h3></body></html>"
             (parse "#### H4")          => "<html><body><h4>H4</h4></body></html>"
             (parse "##### H5")         => "<html><body><h5>H5</h5></body></html>"
             (parse "###### H6")        => "<html><body><h6>H6</h6></body></html>"
             (parse "# H1 #")           => "<html><body><h1>H1</h1></body></html>"
             (parse "## H2 ##")         => "<html><body><h2>H2</h2></body></html>"
             (parse "### H3 ###")       => "<html><body><h3>H3</h3></body></html>"
             (parse "#### H4 ####")     => "<html><body><h4>H4</h4></body></html>"
             (parse "##### H5 #####")   => "<html><body><h5>H5</h5></body></html>"
             (parse "###### H6 ######") => "<html><body><h6>H6</h6></body></html>"
             )

       (fact "handles horizontal rule markers"
             (parse "---")    => "<html><body><hr/></body></html>"
             (parse "___")    => "<html><body><hr/></body></html>"
             (parse "***")    => "<html><body><hr/></body></html>"
             (parse "- - -")  => "<html><body><hr/></body></html>"
             (parse "_ _ _")  => "<html><body><hr/></body></html>"
             (parse "* * *")  => "<html><body><hr/></body></html>"
             (parse "------") => "<html><body><hr/></body></html>"
             (parse "______") => "<html><body><hr/></body></html>"
             (parse "******") => "<html><body><hr/></body></html>"
             )

       (fact "handles links"
             (parse "[text](link title)") => "<html><body><a href=\"link\" title=\"title\">text</a></body></html>"
             (parse "[text](link)")       => "<html><body><a href=\"link\">text</a></body></html>"
             )
       )
