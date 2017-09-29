package std_protocol_if

import chisel3._

class UartIf extends Bundle {
  val TxD = Output(Bool())
  val RxD = Input(Bool())
  val RTS = Output(Bool())
  val CTS = Input(Bool())
  val DSR = Input(Bool())
  val DTR = Output(Bool())
  val DCD = Input(Bool())
  val RI = Input(Bool())
}