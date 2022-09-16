<?php if (!defined('BASEPATH')) exit('No direct script access allowed');

class Items extends CI_Model
{
    public $v_fields=array('cat.cname', 'image');

	function __construct()
	{
		parent::__construct();
	}

	function getList($table="items") {
 
        $this->db->select("$table.*  , cat.cname as catid ");
        $this->db->from($table);
         $this->db->join("cat", "items.catid = cat.cid", "left");     
        $this->db->order_by("id","desc");
        $query = $this->db->get();
        return $result = $query->result();
    }
 

}

