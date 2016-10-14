// The Typesafe repository
resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

resolvers += Resolver.url("bintray-sbt-plugin-releases", url("http://dl.bintray.com/content/sbt/sbt-plugin-releases"))(Resolver.ivyStylePatterns)
       

// Eclipse Plugin
//addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.5.1")
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "5.0.1")
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.3")


