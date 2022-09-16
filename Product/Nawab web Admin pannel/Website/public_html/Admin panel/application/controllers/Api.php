<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Api extends CI_Controller {

    function __construct() {
			parent::__construct();
			$this->load->helper('url');
			$this->load->model('Cat');
			$this->load->model('Items');
    }

 

	public function cat()
	{
 
		$data_cat=$this->Cat->getListTable();			
		$data = array( "status" =>  "1" ,"message" => "category list", "data" => $data_cat );
		echo json_encode($data);
			
	}
	
	
	function items()
	{		
	
		$data_Items=$this->Items->getList();			
		$data = array( "status" =>  "1" ,"message" => "item list", "data" => $data_Items );
		echo json_encode($data);		
		
	}
	 
	      
	
	
	
	function order()
	{

		$id = $this->input->post('id');
		$name = $this->input->post('name');
		$json = $this->input->post('json');
		$email = $this->input->post('email');
		$mobile = $this->input->post('mobile');
		$address = $this->input->post('address');		
 
 
    }

}
/* End of file welcome.php */

/* Location: ./application/controllers/welcome.php */