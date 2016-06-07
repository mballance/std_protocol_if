/****************************************************************************
 * wb_slave_stub.sv
 ****************************************************************************/

/**
 * Module: wb_slave_stub
 * 
 * TODO: Add module documentation
 */
module wb_slave_stub (
		input			clk,
		input			rstn,
		wb_if.slave 	s);
	
	reg req;
	
	always @(posedge clk) begin
		if (rstn == 0) begin
			req <= 0;
		end else begin
			if (s.CYC && s.STB && !req) begin
				req <= 1;
			end else begin
				req <= 0;
			end
		end
	end
	
	assign s.DAT_R = 'hdeadbeef;
	assign s.ACK = req;
	assign s.ERR = 0;

endmodule


