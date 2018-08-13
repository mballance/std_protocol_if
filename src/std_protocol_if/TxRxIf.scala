package std_protocol_if

import chisel3._

class TxRxIf extends Bundle {
  val TxD = Output(Bool())
  val RxD = Input(Bool())
}