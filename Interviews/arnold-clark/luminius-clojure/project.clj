(defproject luminius-clojure "0.1.0-SNAPSHOT"

  :description "FIXME: write description"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [ring-server "0.3.1"]
                 [selmer "0.8.0"]
                 [com.taoensso/timbre "3.3.1"]
                 [com.taoensso/tower "3.0.2"]
                 [markdown-clj "0.9.64"]
                 [environ "1.0.0"]
                 [im.chit/cronj "1.4.3"]
                 [compojure "1.3.2"]
                 [ring/ring-defaults "0.1.3"]
                 [ring/ring-session-timeout "0.1.0"]
                 [ring-middleware-format "0.4.0"]
                 [noir-exception "0.2.3"]
                 [bouncer "0.3.2"]
                 [prone "0.8.0"]
                 ]

  :min-lein-version "2.0.0"
  :uberjar-name "luminius-clojure.jar"
  :repl-options {:init-ns luminius-clojure.handler}
  :jvm-opts ["-server"]

  :main luminius-clojure.core

  :plugins [[lein-ring "0.9.1"]
            [lein-environ "1.0.0"]
            [lein-ancient "0.6.0"]
            ]
  

  :ring {:handler luminius-clojure.handler/app
         :init    luminius-clojure.handler/init
         :destroy luminius-clojure.handler/destroy
         :uberwar-name "luminius-clojure.war"}
  

  
  :profiles
  {:uberjar {:omit-source true
             :env {:production true}
            
             :aot :all}
   :production {:ring {:open-browser? false
                       :stacktraces?  false
                       :auto-reload?  false}}
   :dev {:dependencies [[ring-mock "0.1.5"]
                        [ring/ring-devel "1.3.2"]
                        [pjstadig/humane-test-output "0.6.0"]
                        ]
         
         
        
         
         :injections [(require 'pjstadig.humane-test-output)
                      (pjstadig.humane-test-output/activate!)]
         :env {:dev true}}})