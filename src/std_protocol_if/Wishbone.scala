package std_protocol_if

import chisel3.Bool
import chisel3.Input
import chisel3.Output
import chisel3.UInt
import chisel3.core.Bundle
import chisel3.fromIntToWidth
import chisel3.fromtIntToLiteral
import chisellib.ParameterizedBundle
import chisellib.ParametersBase
import chisellib.ParameterizedBundle
import chisellib.ParameterizedBundle
import chisel3.core.Wire
import chisellib.ParameterizedBundle
import chisellib.ParameterizedBundle

class Wishbone(val p : Wishbone.Parameters) extends Bundle {

	val req = new Wishbone.ReqData(p)
	val rsp = new Wishbone.RspData(p)
	
  override def cloneType() : this.type = {
 	  return new Wishbone(p).asInstanceOf[this.type]
  }	
}


object Wishbone {
class Parameters (
    val ADDR_WIDTH  :  Int=32,
    val DATA_WIDTH  :  Int=32,
    val TGA_WIDTH   :  Int=1,
    val TGD_WIDTH   :  Int=1,
    val TGC_WIDTH   :  Int=1) extends ParametersBase { }

	class RspData(override val p : Wishbone.Parameters) extends ParameterizedBundle(p) {
	  
	val DAT_R = Input(UInt(p.DATA_WIDTH.W))
	val TGD_R = Input(UInt(p.TGD_WIDTH.W))
	val ERR = Input(Bool())
	val ACK = Input(Bool())	  

	def park() {
			DAT_R := 0.asUInt();
		TGD_R := 0.asUInt();
		ERR := Bool(false);
		ACK := Bool(false);
		}

		def set_error() {
			DAT_R := 0.asUInt();
      TGD_R := 0.asUInt();
      ERR := Bool(true);
      ACK := Bool(true);
		}
		
  override def cloneType() : this.type = {
 	  return new RspData(p).asInstanceOf[this.type]
  }	
	}

	class ReqData(override val p : Wishbone.Parameters) extends ParameterizedBundle(p) {
    val ADR = Output(UInt(p.ADDR_WIDTH.W))
    val TGA = Output(UInt(p.TGA_WIDTH.W))
	val CTI = Output(UInt(3.W))
	val BTE = Output(UInt(2.W))
	val DAT_W = Output(UInt(p.DATA_WIDTH.W))
	val TGD_W = Output(UInt(p.TGD_WIDTH.W))
	val CYC = Output(Bool())
	val TGC = Output(UInt(p.TGC_WIDTH.W))
	val SEL = Output(UInt((p.DATA_WIDTH/8).W))
	val STB = Output(Bool())
	val	WE = Output(Bool())
	
	def assign_2b(w : Wishbone) : ReqData = {
	  ADR := w.req.ADR
	  TGA := w.req.TGA
	  CTI := w.req.CTI
	  BTE := w.req.BTE
	  DAT_W := w.req.DAT_W
	  TGD_W := w.req.TGD_W
	  CYC := w.req.CYC
	  TGC := w.req.TGC
	  SEL := w.req.SEL
	  STB := w.req.STB
	  WE := w.req.WE
	  this
	}
	
	def park() {
	  ADR := 0.asUInt()
	  TGA := 0.asUInt()
	  CTI := 0.asUInt()
	  BTE := 0.asUInt()
	  DAT_W := 0.asUInt()
	  TGD_W := 0.asUInt()
	  CYC := Bool(false)
	  TGC := 0.asUInt()
	  SEL := 0.asUInt()
	  STB := Bool(false)
	  WE := Bool(false)
	}
	
  override def cloneType() : this.type = {
 	  return new ReqData(p).asInstanceOf[this.type]
  }	
	}

}

