<?php $this->load->view('header'); ?>
<!--  BO :heading -->
<div class="row wrapper border-bottom white-bg page-heading">
  <div class="col-sm-4">
    <h2>Orderdata</h2>
    <ol class="breadcrumb">
      <li>
        <a href="<?php echo base_url().'admin/'?>">Dashboard</a>
      </li>
      <li class="active">
        <strong>Orderdata</strong>
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
  <div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox ">
      <div class="ibox-title" >
        <h5>Add <small></small></h5>
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
          <input type="hidden" name="<?php echo $this->security->get_csrf_token_name(); ?>" value="<?php echo $this->security->get_csrf_hash(); ?>">
            <div class="box-body">
              <?php if($this->session->flashdata('message')): ?>
              <div class="alert alert-success">
                <button type="button" class="close" data-close="alert"></button>
                <?php echo $this->session->flashdata('message'); ?>
              </div>
              <?php endif; ?> 
              





	<!-- Name Start -->

	<div class="form-group">

	  <label for="name" class="col-sm-3 control-label"> Name </label>

	  <div class="col-sm-4">

	    <input type="text" class="form-control" id="name" name="name" 

	    

	    value="<?php echo set_value("name"); ?>"

	    >

	  </div>

	  <div class="col-sm-5" >

	 

	    <?php echo form_error("name","<span class='label label-danger'>","</span>")?>

	  </div>

	</div> 

	<!-- Name End -->





	





	<!-- Json Start -->

	<div class="form-group">

	  <label for="json" class="col-sm-3 control-label"> Json </label>

	  <div class="col-sm-4">

	    <input type="text" class="form-control" id="json" name="json" 

	    

	    value="<?php echo set_value("json"); ?>"

	    >

	  </div>

	  <div class="col-sm-5" >

	 

	    <?php echo form_error("json","<span class='label label-danger'>","</span>")?>

	  </div>

	</div> 

	<!-- Json End -->





	





	<!-- Email Start -->

	<div class="form-group">

	  <label for="email" class="col-sm-3 control-label"> Email </label>

	  <div class="col-sm-4">

	    <input type="text" class="form-control" id="email" name="email" 

	    

	    value="<?php echo set_value("email"); ?>"

	    >

	  </div>

	  <div class="col-sm-5" >

	 

	    <?php echo form_error("email","<span class='label label-danger'>","</span>")?>

	  </div>

	</div> 

	<!-- Email End -->





	





	<!-- Mobile Start -->

	<div class="form-group">

	  <label for="mobile" class="col-sm-3 control-label"> Mobile </label>

	  <div class="col-sm-4">

	    <input type="text" class="form-control" id="mobile" name="mobile" 

	    

	    value="<?php echo set_value("mobile"); ?>"

	    >

	  </div>

	  <div class="col-sm-5" >

	 

	    <?php echo form_error("mobile","<span class='label label-danger'>","</span>")?>

	  </div>

	</div> 

	<!-- Mobile End -->





	





	<!-- Address Start -->

	<div class="form-group">

	  <label for="address" class="col-sm-3 control-label"> Address </label>

	  <div class="col-sm-4">

	    <input type="text" class="form-control" id="address" name="address" 

	    

	    value="<?php echo set_value("address"); ?>"

	    >

	  </div>

	  <div class="col-sm-5" >

	 

	    <?php echo form_error("address","<span class='label label-danger'>","</span>")?>

	  </div>

	</div> 

	<!-- Address End -->





	
              <div class="form-group">
                <div class="col-sm-3" >                       
                </div>
                <div class="col-sm-6">
                  <button type="reset" class="btn btn-default ">Reset</button>
                  <button type="submit" class="btn btn-info ">Submit</button>
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