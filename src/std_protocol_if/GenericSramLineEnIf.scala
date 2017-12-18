package std_protocol_if

import chisel3._
import chisellib._

class GenericSramLineEnIf(override val p : GenericSramLineEnIf.Parameters) extends ParameterizedBundle(p) {
  val addr = Output(UInt(p.NUM_ADDR_BITS.W))
  val write_data = Output(UInt(p.NUM_DATA_BITS.W))
  val write_en = Output(Bool())
  val read_en = Output(Bool())
  
  val read_data = Input(UInt(p.NUM_DATA_BITS.W))
}

object GenericSramLineEnIf {
  class Parameters(
      val NUM_ADDR_BITS : Int=32,
      val NUM_DATA_BITS : Int=32) { }
}