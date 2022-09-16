<?php $this->load->view('header'); ?>
<!--  BO :heading -->
<div class="row wrapper border-bottom white-bg page-heading">
   <div class="col-sm-4">
      <h2>Items</h2>
      <ol class="breadcrumb">
         <li>
            <a href="<?php echo base_url().'admin/'?>">Dashboard</a>
         </li>
         <li class="active">
            <strong>Items</strong>
         </li>
      </ol>
   </div>
   <div class="col-sm-8">
      <div class="title-action">
      </div>
   </div>
</div>
<!--  EO :heading -->
<div class="row">
   <div class="col-lg-12 row wrapper ">
      <div class="ibox ">
         <div class="ibox-title" >
            <h5>View <small></small></h5>
            <div class="ibox-tools">                           
            </div>
         </div>
         <!-- ............................................................. -->
         <!-- BO : content  -->
         <div class="col-sm-12 white-bg ">
            <div class="box box-info">
               <div class="box-header with-border">
                  <h3 class="box-title">  </h3>
               </div>
               <!-- /.box-header -->
               <!-- form start -->
               <form action="" id="" class="form-horizontal " method="post" enctype="multipart/form-data">
                  <div class="box-body">
                     <?php if($this->session->flashdata('message')): ?>
                     <div class="alert alert-success">
                        <button type="button" class="close" data-close="alert"></button>
                        <?php echo $this->session->flashdata('message'); ?>
                     </div>
                     <?php endif; ?> 
                     

<table class='table table-bordered' style='width:70%;' align='center'>

	<tr>

	 <td>

	   <label for="name" class="col-sm-3 control-label"> Name </label>

	 </td>

	 <td> 

	   <?php echo set_value("name",html_entity_decode($items->name)); ?>

	 </td>

	</tr>

	



    <!-- Catid Start -->

	<tr>

	 <td>

	  <label class="control-label col-md-3"> Catid </label>

	 </td>

	 <td> 

	   <?php 

	      if(isset($cat) && !empty($cat)):



	      foreach($cat as $key => $value): 

	       if($value->cid==$items->catid)

	             echo $value->cname;



	       endforeach; 

	       endif; ?>

	 </td>

	</tr>

    <!-- Catid End -->



	

	<tr>

	 <td>

	   <label for="price" class="col-sm-3 control-label"> Price </label>

	 </td>

	 <td> 

	   <?php echo set_value("price",html_entity_decode($items->price)); ?>

	 </td>

	</tr>

	



    <!-- Image Start -->

	<tr>

	 <td>

	  <label for="address" class="col-sm-3 control-label"> Image </label>

	 </td>

	 <td>

	 <?php if (isset($items->image) && $items->image!=""){?>

	            <br>

	    <img src="<?php echo $this->config->item("photo_url");?><?php echo $items->image; ?>" alt="pic" width="50" height="50" />

	    <?php } ?>

	 </td>

	</tr>

    <!-- Image End -->



	



    <!-- Desc Start -->

	<tr>

	 <td>

	  <label for="desc" class="col-sm-3 control-label"> Desc </label>

	 </td>

	 <td> 

	   <?php echo set_value("desc",  html_entity_decode($items->desc)); ?>

	 </td>

	</tr>

    <!-- Desc End -->



	<tr><td colspan="2"><a type="reset" class="btn btn-info pull-right" onclick="history.back()">Back</a></td></tr></table>
                     <div class="form-group">
                        <div class="col-sm-3" >                       
                        </div>
                        <div class="col-sm-6">
                        </div>
                        <div class="col-sm-3" >                       
                        </div>
                     </div>
                  </div>
                  <!-- /.box-body -->
                  <div class="box-footer">
                  </div>
                  <!-- /.box-footer -->
               </form>
            </div>
            <!-- /.box -->
            <br><br><br><br>
         </div>
         <!-- EO : content  -->
         <!-- ...................................................................... -->
      </div>
   </div>
</div>
<?php $this->load->view('footer'); ?>