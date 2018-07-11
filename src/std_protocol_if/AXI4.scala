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
  
  def tieoff() {
    awready := Bool(false)
    brsp.tieoff()
    arready := Bool(false)
    rresp.tieoff()
  }
  
  def tieoff_flipped() {
    awreq.tieoff_flipped()
    arreq.tieoff_flipped()
    wreq.tieoff_flipped()
    bready := Bool(false)
    rready := Bool(false)
  }
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
   
    def tieoff_flipped() {
      AWADDR := 0.asUInt()
      AWID := 0.asUInt()
      AWLEN := 0.asUInt()
      AWSIZE := 0.asUInt()
      AWBURST := 0.asUInt()
      AWLOCK := Bool(false)
      AWCACHE := 0.asUInt()
      AWPROT := 0.asUInt()
      AWQOS := 0.asUInt()
      AWREGION := 0.asUInt()
      AWVALID := Bool(false)
    }
  }
  
  class WReq(override val p : AXI4.Parameters) extends ParameterizedBundle(p) {
    val WDATA = Output(UInt(p.DATA_WIDTH.W))
    val WSTRB = Output(UInt((p.DATA_WIDTH/8).W))
    val WLAST = Output(Bool())
    val WVALID = Output(Bool())
    
    def tieoff_flipped() {
      WDATA := 0.asUInt()
      WSTRB := 0.asUInt()
      WLAST := 0.asUInt()
      WVALID := 0.asUInt()
    }    
  }
  
  class BRsp(override val p : AXI4.Parameters) extends ParameterizedBundle(p) {
    val BID = Input(UInt(p.ID_WIDTH.W))
    val BRESP = Input(UInt(2.W))
    val BVALID = Input(Bool())
    
    def tieoff() {
      BID := 0.asUInt()
      BRESP := 0.asUInt()
      BVALID := Bool(false)
    }    
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
    
    def tieoff_flipped() {
      ARADDR := 0.asUInt()
      ARID := 0.asUInt()
      ARLEN := 0.asUInt()
      ARSIZE := 0.asUInt()
      ARBURST := 0.asUInt()
      ARLOCK := Bool(false)
      ARCACHE := 0.asUInt()
      ARPROT := 0.asUInt()
      ARQOS := 0.asUInt()
      ARREGION := 0.asUInt()
      ARVALID := Bool(false)
    }    
  }
  
  class RRsp(override val p : AXI4.Parameters) extends ParameterizedBundle(p) {
    val RID = Input(UInt(p.ID_WIDTH.W))
    val RDATA = Input(UInt(p.DATA_WIDTH.W))
    val RRESP = Input(UInt(2.W))
    val RLAST = Input(Bool())
    val RVALID = Input(Bool())
    
    def tieoff() {
      RID    := 0.asUInt()
      RDATA  := 0.asUInt()
      RRESP  := 0.asUInt()
      RLAST  := Bool(false)
      RVALID := Bool(false)
    }    
  }
}

