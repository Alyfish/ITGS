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





	



	<!-- Catid Start -->

	<div class="form-group">

        <label class="control-label col-md-3"> Catid </label>

          <div class="col-md-4">

              <select class="form-control select2" name="catid" id="catid">

              <option value="">Select Catid</option>

      <?php 

      if(isset($cat) && !empty($cat)):

      foreach($cat as $key => $value): ?>

          <option value="<?php echo $value->cid; ?>">

            <?php echo $value->cname; ?>

          </option>

      <?php endforeach; ?>

      <?php endif; ?>

      </select>

        </div>

    </div>

      <!-- Catid End -->









	<!-- Price Start -->

	<div class="form-group">

	  <label for="price" class="col-sm-3 control-label"> Price </label>

	  <div class="col-sm-4">

	    <input type="text" class="form-control" id="price" name="price" 

	    

	    value="<?php echo set_value("price"); ?>"

	    >

	  </div>

	  <div class="col-sm-5" >

	 

	    <?php echo form_error("price","<span class='label label-danger'>","</span>")?>

	  </div>

	</div> 

	<!-- Price End -->





	



    <!-- Image Start -->

    <div class="form-group">

      <label for="address" class="col-sm-3 control-label"> Image </label>

      <div class="col-sm-6">

      <input type="file" name="image" />

      <input type="hidden" name="old_image" value="<?php if (isset($image) && $image!=""){echo $image; } ?>" />

        <?php if(isset($image_err) && !empty($image_err)) 

        { foreach($image_err as $key => $error)

        { echo "<div class=\"error-msg\"></div>"; } }?>

      </div>

        <div class="col-sm-3" >

      </div>

    </div>

    <!-- Image End -->



    



				<!-- Desc Start -->

			<div class="form-group">

			  <label for="desc" class="col-sm-3 control-label"> Desc </label>

			  <div class="col-sm-4">

			    <textarea class="form-control" id="desc" name="desc"><?php echo set_value("desc"); ?></textarea>

			  </div>

			  <div class="col-sm-5" >

			 

			    <?php echo form_error("desc","<span class='label label-danger'>","</span>")?>

			  </div>

			</div> 

			<!-- Desc End -->



			
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