/****************************************************************************
 * wb_master_stub.sv
 ****************************************************************************/

/**
 * Module: wb_master_stub
 * 
 * Stubs out a master interface. This is used when an unused slave interface
 * must be terminated
 */
module wb_master_stub (wb_if.master	m);
	
	assign m.ADR = 0;
	assign m.CYC = 0;
	assign m.DAT_W = 0;
	assign m.SEL = 0;
	assign m.STB = 0;
	assign m.WE = 0;
endmodule


