import com.github.dunnololda.scage.ScageScreenApp
import com.github.dunnololda.scage.ScageLib._
import com.github.dunnololda.scage.support.Vec

object HelloWorldExample extends ScageScreenApp("Hello World", 640, 480) {
  private var ang = 0f
  action(100) {
    ang += 5
  }

  backgroundColor = BLACK
  render {
    openglMove(windowSize/2)
    openglRotate(ang)
    print("Hello World!", Vec(-50, -5), GREEN)
  }
}
