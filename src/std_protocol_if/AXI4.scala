/****************************************************************************
 * AXI4.scala
 * 
 * Chisel definition of an AXI4 protocol interface
 ****************************************************************************/
package std_protocol_if

import chisellib._
import chisellib.ParametersBase
import chisellib.ParameterizedBundle
import chisel3._


class AXI4(override val p : AXI4.Parameters) extends ParameterizedBundle(p) {
  val awreq = new AXI4.AWReq(p)
  val awready = Input(Bool())
  
  val arreq = new AXI4.ARReq(p)
  val arready = Input(Bool())
  
  val wreq = new AXI4.WReq(p)
  val wready = Input(Bool())
  
  val brsp = new AXI4.BRsp(p)
  val bready = Output(Bool())
  
  val rresp = new AXI4.RRsp(p)
  val rready = Output(Bool())
}

object AXI4 {
  class Parameters (
      val ADDR_WIDTH : Int = 32,
      val DATA_WIDTH : Int = 128,
      val ID_WIDTH : Int = 4
      ) extends ParametersBase { }
  
  class AWReq(override val p : AXI4.Parameters) extends ParameterizedBundle(p) {
    val AWADDR = Output(UInt(p.ADDR_WIDTH.W))
    val AWID = Output(UInt(p.ID_WIDTH.W))
    val AWLEN = Output(UInt(8.W))
    val AWSIZE = Output(UInt(3.W))
    val AWBURST = Output(UInt(2.W))
    val AWLOCK = Output(Bool())
    val AWCACHE = Output(UInt(4.W))
    val AWPROT = Output(UInt(3.W))
    val AWQOS = Output(UInt(4.W))
    val AWREGION = Output(UInt(4.W))
    val AWVALID = Output(Bool())
  }
  
  class WReq(override val p : AXI4.Parameters) extends ParameterizedBundle(p) {
    val WDATA = Output(UInt(p.DATA_WIDTH.W))
    val WSTRB = Output(UInt((p.DATA_WIDTH/8).W))
    val WLAST = Output(Bool())
    val WVALID = Output(Bool())
  }
  
  class BRsp(override val p : AXI4.Parameters) extends ParameterizedBundle(p) {
    val BID = Input(UInt(p.ID_WIDTH.W))
    val BRESP = Input(UInt(2))
    val BVALID = Input(Bool())
  }
  
  class ARReq(override val p : AXI4.Parameters) extends ParameterizedBundle(p) {
    val ARADDR = Output(UInt(p.ADDR_WIDTH.W))
    val ARID = Output(UInt(p.ID_WIDTH.W))
    val ARLEN = Output(UInt(8.W))
    val ARSIZE = Output(UInt(3.W))
    val ARBURST = Output(UInt(2.W))
    val ARLOCK = Output(Bool())
    val ARCACHE = Output(UInt(4.W))
    val ARPROT = Output(UInt(3.W))
    val ARQOS = Output(UInt(4.W))
    val ARREGION = Output(UInt(4.W))
    val ARVALID = Output(Bool())
  }
  
  class RRsp(override val p : AXI4.Parameters) extends ParameterizedBundle(p) {
    val RID = Input(UInt(p.ID_WIDTH.W))
    val RDATA = Input(UInt(p.DATA_WIDTH.W))
    val RRESP = Input(UInt(2.W))
    val RLAST = Input(Bool())
    val RVALID = Input(Bool())
  }
}

