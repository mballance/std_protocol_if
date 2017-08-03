package std_protocol_if

import chisel3._

class WishboneParameters (
    val ADDR_WIDTH  :  Int=32,
    val DATA_WIDTH  :  Int=32,
    val TGA_WIDTH   :  Int=1,
    val TGD_WIDTH   :  Int=1,
    val TGC_WIDTH   :  Int=1) {
  
    def cloneType() = (new WishboneParameters(ADDR_WIDTH, DATA_WIDTH,
        TGA_WIDTH, TGD_WIDTH, TGC_WIDTH)).asInstanceOf[this.type]
}

class WishboneMaster(val p : WishboneParameters) extends Bundle {
	val ADR = Output(UInt(p.ADDR_WIDTH.W));
	val TGA = Output(UInt(p.TGA_WIDTH.W));
	val CTI = Output(UInt(3.W));
	val BTE = Output(UInt(2.W));
	val DAT_W = Output(UInt(p.DATA_WIDTH.W));
	val TGD_W = Output(UInt(p.TGD_WIDTH.W));
	val DAT_R = Input(UInt(p.DATA_WIDTH.W));
	val TGD_R = Input(UInt(p.TGD_WIDTH.W));
	val CYC = Output(Bool());
	val TGC = Output(UInt(p.TGC_WIDTH.W));
	val ERR = Input(Bool());
	val SEL = Output(UInt(p.DATA_WIDTH/8));
	val STB = Output(Bool());
	val ACK = Input(Bool());
	val	WE = Output(Bool());

	// Assigns the request fields to the parameter
	def assign_req2p(m : WishboneMaster) {
   	  m.ADR := ADR;
   	  m.TGA := TGA;
   	  m.CTI := CTI;
   	  m.BTE := BTE;
   	  m.DAT_W := DAT_W;
   	  m.TGD_W := TGD_W;
   	  m.CYC := CYC;
   	  m.TGC := TGC;
   	  m.SEL := SEL;
   	  m.STB := STB;
   	  m.WE := WE;	  
	}
	
	def assign_rsp2p(m : WishboneMaster) {
	    m.DAT_R := DAT_R;
	    m.TGD_R := TGD_R;
	    m.ERR := ERR;
	    m.ACK := ACK;	  
	}

	// Assigns inactive values to all request signals
	def park_rsp() {
	    DAT_R := 0.asUInt(p.DATA_WIDTH.W);
	    TGD_R := 0.asUInt(p.TGD_WIDTH.W);
	    ERR := Bool(false);
	    ACK := Bool(false);
	}
	
	// Assigns m to the request fields
	def assign_p2req(m : WishboneMaster) {
		ADR := m.ADR
		TGA := m.TGA
		CTI := m.CTI
		BTE := m.BTE
		DAT_W := m.DAT_W
	  TGD_W := m.TGD_W
	  CYC := m.CYC
	  TGC := m.TGC
	  SEL := m.SEL
	  STB := m.STB
	  WE := m.WE	 
	}
 
  override def cloneType() = (new WishboneMaster(p)).asInstanceOf[this.type]
}
