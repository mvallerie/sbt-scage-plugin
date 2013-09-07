import sbt._
import Keys._
import Project.Initialize
import io.Source
import Classpaths.managedJars

// All credits for some parts of the code go to philcali
// @see https://github.com/philcali/sbt-lwjgl-plugin/
object ScagePlugin extends Plugin {
    object scage {
	val version = SettingKey[String]("scage-version")
    }

    lazy val phys2DPatch = TaskKey[Unit]("phys2D-patch", "The phys2d dependency pom is broken. Patch aims to fix it")

    lazy val nativesExtract = TaskKey[Unit]("natives-extract", "Extracts LWJGL Native JAR file")

    def defineOs = System.getProperty("os.name").toLowerCase.take(3).toString match {
    	case "lin" => ("linux", "so")
    	case "mac" | "dar" => ("macosx", "lib")
    	case "win" => ("windows", "dll")
    	case "sun" => ("solaris", "so")
    	case _ => ("unknown", "")
    }

    private def phys2DPatchTask : Initialize[sbt.Task[Unit]] = (streams, ivyPaths) map { (s, ivys) =>
        val base = ivys.ivyHome.getOrElse(Path.userHome / ".ivy2")

        val path = base / "cache" / "phys2d" / "phys2d" / "ivy-060408.xml"

        if (path.exists) {
        	s.log.info("Patching %s ..." format(path))
                val pattern = "zip".r
                val ivysource = Source.fromFile(path)
                val text = ivysource.getLines.mkString
                val writer = new java.io.FileWriter(path)
                writer.write(pattern.replaceAllIn(text, "jar"))
                writer.close
                s.log.info("Done.")
        } else {
                s.log.warn("Update might fail. This is expected.")
                s.log.warn("Please run update one more time.")
        }
    }

    private def nativesExtractTask : Initialize[sbt.Task[Unit]] = (streams, classpathTypes, update) map { (s, ct, up) =>
    	val natives = managedJars(Compile, ct, up) map { _.data } find { _.getName.startsWith("lwjgl-native") }
        natives map {
        	val target = file(".") / "libs" / "natives"
                s.log.info("Extracting LWJGL natives to " + target)
                IO.unzip(_, target)
        } getOrElse {
                s.log.warn("Unable to find LWJGL natives jar, try to update again.")
        }
    }

    lazy val scageSettings: Seq[Setting[_]] = Seq(
	scage.version := "0.9.3-SNAPSHOT",
    	phys2DPatch <<= phys2DPatchTask,
	nativesExtract <<= nativesExtractTask,

	resolvers ++= Seq(
		"dunnololda's maven repo" at "https://raw.github.com/dunnololda/mvn-repo/master",
		"lwjgl" at "http://adterrasperaspera.com/lwjgl",
        	"Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
        	"FreeHEP Repository" at "http://java.freehep.org/maven2",
        	"B2S Repository" at "http://b2s-repo.googlecode.com/svn/trunk/mvn-repo",
        	"Scala-Tools Maven2 Repository" at "https://oss.sonatype.org/content/groups/scala-tools/"
	),
	libraryDependencies <+= (scage.version) { "com.github.dunnololda.scage" % "scage" % _ },

	// TODO should run on "update" only.
	update <<= update dependsOn phys2DPatch,
	// TODO : UH ?
	run <<= run in Runtime dependsOn nativesExtract,

	fork := true,
	javaOptions ++= Seq("-Djava.library.path=%s".format(file(".") / "libs" / "natives" / defineOs._1), "-DLWJGL_DISABLE_XRANDR=true")
    )

}
