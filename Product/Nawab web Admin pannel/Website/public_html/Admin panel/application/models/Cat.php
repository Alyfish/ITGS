<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

class Cat extends CI_Model
{
    public $v_fields=array('cname');

	function __construct()
	{
		parent::__construct();
	}

 
    function getListTable($table="cat") {
        $this->db->select("*");
        $this->db->from($table);
        $query = $this->db->get();
        return $result = $query->result_array();
    }
 

}

