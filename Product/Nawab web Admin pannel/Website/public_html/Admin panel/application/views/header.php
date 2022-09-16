<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title> CI GENERATOR </title>
        <link href="<?php echo $this->config->item('accet_url') ?>css/plugins/select2/select2.min.css" rel="stylesheet">
        <script src="<?php echo $this->config->item('accet_url') ?>js/jquery-2.1.1.min.js"></script>
         <script src="<?php echo $this->config->item('accet_url') ?>js/plugins/slimscroll/jquery.slimscroll.min.js"></script> 
        <script src="<?php echo $this->config->item('accet_url') ?>js/app.js"></script>
        <script src="<?php echo $this->config->item('accet_url') ?>js/plugins/pace/pace.min.js"></script>
        <link href="<?php echo $this->config->item('accet_url') ?>css/plugins/chosen/chosen.css" rel="stylesheet">
        <link href="<?php echo $this->config->item('accet_url') ?>css/bootstrap.min.css" rel="stylesheet">
        <link href="<?php echo $this->config->item('accet_url') ?>font-awesome/css/font-awesome.css" rel="stylesheet">
        <!-- Toastr style -->
        <link href="<?php echo $this->config->item('accet_url') ?>css/plugins/toastr/toastr.min.css" rel="stylesheet">
        <!-- Gritter -->
        <link href="<?php echo $this->config->item('accet_url') ?>js/plugins/gritter/jquery.gritter.css" rel="stylesheet">
        <link href="<?php echo $this->config->item('accet_url') ?>css/animate.css" rel="stylesheet">
        <link href="<?php echo $this->config->item('accet_url') ?>css/style.css" rel="stylesheet">
        <link href="<?php echo $this->config->item('accet_url') ?>css/plugins/dataTables/datatables.min.css" rel="stylesheet">
        <!-- Date Picker-->
        <link href="<?php echo base_url() ?>accets/datepicker/datepicker.css" rel="stylesheet">
        <script src="<?php echo base_url() ?>accets/datepicker/bootstrap-datepicker.js"></script>
        <link href="<?php echo $this->config->item('accet_url') ?>css/plugins/clockpicker/clockpicker.css" rel="stylesheet">
        <script src="<?php echo $this->config->item('accet_url') ?>js/recordDel.js"></script>
        <link href="<?php echo $this->config->item('accet_url') ?>css/bootstrap-datetimepicker.css" rel="stylesheet">
    </head>
    <body>
        <div id="wrapper">
            <nav class="navbar-default navbar-static-side" role="navigation">
                <div class="sidebar-collapse">
                    <ul class="nav metismenu" id="side-menu">
 
                <!-- BO : left nav  -->
                <?php
                $contr = $this->uri->segment(2);
                $action = $this->uri->segment(3);
                $actionNext = $this->uri->segment(4);
                if (!empty($action)) {
                    $module = $contr . '/' . $action;
                    if (!empty($actionNext)) {
                        $module = $contr . '/' . $action . '/' . $actionNext;
                    }
                } else {
                    $module = $contr;
                }
                $contrnew = $contr . '/' . $action;
                ?> 
                <!-- BO : dashboard -->
                <li  <?php if ($contr == '') { ?>class="active "<?php } ?>>
                    <a href="<?php echo base_url() . 'admin' ?>"><i class="fa fa-home"></i><span class="title">Dashboard</span>
                        <?php if ($module == '') { ?><span class="selected"></span><?php } ?>
                    </a>
                </li>
                <!-- EO : dashboard -->

                <!-- BO : Modules
                <li  <?php if ($contr == 'module') { ?>class="active "<?php } ?>  >
                    <a href="<?php echo base_url() ?>admin/module/add"><i class="fa fa-users"></i><span class="title">Generate Module</span>
                        <?php if ($contr == 'module') { ?><span class="selected"></span><?php } ?>   
                        <span class="arrow <?php if ($contr == 'module') { ?>open<?php } ?>"></span>
                    </a>
                </li> -->
                <!--  EO : Modules -->
                
               



				<!-- BO : Cat -->

                <li <?php if($contr == 'cat'){?>class="active "<?php } ?>  >

                    <a href="javascript:;"><i class="fa fa-users"></i><span class="title">Cat</span>

                        <?php if($contr == 'cat'){?><span class="selected"></span><?php } ?>

                      <span class="arrow <?php if($contr == 'cat'){?>open<?php } ?>"></span>

                    </a>

                    <ul class="nav nav-second-level">

                      <li <?php if($contrnew == 'cat/add'){?>class="active "<?php } ?>>

                        <a href="<?php echo base_url()?>admin/cat/add"><i class="fa fa-angle-double-right">

                            </i>Add Cat</a>

                      </li>

                      <li <?php if($contrnew == 'cat/'){?>class="active"<?php } ?>>

                        <a href="<?php echo base_url()?>admin/cat/index"><i class="fa fa-gear"></i>Manage Cat</a>

                      </li>                       

                    </ul>

                </li>

                <!--  EO : Cat -->



               



				<!-- BO : Items -->

                <li <?php if($contr == 'items'){?>class="active "<?php } ?>  >

                    <a href="javascript:;"><i class="fa fa-users"></i><span class="title">Items</span>

                        <?php if($contr == 'items'){?><span class="selected"></span><?php } ?>

                      <span class="arrow <?php if($contr == 'items'){?>open<?php } ?>"></span>

                    </a>

                    <ul class="nav nav-second-level">

                      <li <?php if($contrnew == 'items/add'){?>class="active "<?php } ?>>

                        <a href="<?php echo base_url()?>admin/items/add"><i class="fa fa-angle-double-right">

                            </i>Add Items</a>

                      </li>

                      <li <?php if($contrnew == 'items/'){?>class="active"<?php } ?>>

                        <a href="<?php echo base_url()?>admin/items/index"><i class="fa fa-gear"></i>Manage Items</a>

                      </li>                       

                    </ul>

                </li>

                <!--  EO : Items -->



               



				<!-- BO : Orderdata -->

                <li <?php if($contr == 'orderdata'){?>class="active "<?php } ?>  >

                    <a href="javascript:;"><i class="fa fa-users"></i><span class="title">Orderdata</span>

                        <?php if($contr == 'orderdata'){?><span class="selected"></span><?php } ?>

                      <span class="arrow <?php if($contr == 'orderdata'){?>open<?php } ?>"></span>

                    </a>

                    <ul class="nav nav-second-level">

                    <!-----  <li <?php if($contrnew == 'orderdata/add'){?>class="active "<?php } ?>>

                        <a href="<?php echo base_url()?>admin/orderdata/add"><i class="fa fa-angle-double-right">

                            </i>Add Orderdata</a>

                      </li>   ---->

                      <li <?php if($contrnew == 'orderdata/'){?>class="active"<?php } ?>>

                        <a href="<?php echo base_url()?>admin/orderdata/index"><i class="fa fa-gear"></i>Manage Orderdata</a>

                      </li>                       

                    </ul>

                </li>

                <!--  EO : Orderdata -->



               <!--  @@@@@#####@@@@@ -->



                



                



                

                <li><a href="<?php echo $this->config->item('left_url') ?>password"><i class="fa fa-users"></i><span class="title">Change Password</span></a></li>
                <li><a href="<?php echo $this->config->item('left_url') ?>auth/logout"><i class="fa fa-users"></i><span class="title">Logout</span></a></li>
                    </ul>
                </div>
            </nav>
            <div id="page-wrapper" class="gray-bg dashbard-1">
                <div class="row border-bottom">
                    <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                        <div class="navbar-header">
                            <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
                        </div>
                        <ul class="nav navbar-top-links navbar-right">
                            <li>
                                <span class="m-r-sm text-muted welcome-message">Welcome <?php
                                    if (isset($username) and ! empty($username)) {
                                        echo $username;
                                    }
                                    ?> </span>
                            </li>
                            <li class="dropdown">
                                <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                    <span class="clear"> 
                                        <span class="text-muted text-xs block">
                                            <img src="<?php echo $this->config->item('accet_url') ?>img/user.png" class="img-circle" alt="User Image" width="20px">
                                        </span> 
                                    </span>
                                </a>
                                <span>
                                </span>
                                <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                   <!-- <li><a href="<?php echo $this->config->item('left_url') ?>admin/profile/edit">Profile</a></li> -->
                                    <li><a href="<?php echo $this->config->item('left_url') ?>password">Change Password </a></li>
                                    <li class="divider"></li>
                                    <li><a href="<?php echo $this->config->item('left_url') ?>auth/logout">Logout</a></li>
                                </ul>
                            </li>
                        </ul>
                    </nav>
                </div>
                <!-- Common Delete Popup  -->
                <div class="modal fade" id="commonDelete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <form action="<?php echo base_url() ?>welcome/delete_notification/" method="post">
                    <input type="hidden" name="<?php echo $this->security->get_csrf_token_name(); ?>" value="<?php echo $this->security->get_csrf_hash(); ?>">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                    <h4 class="modal-title" id="frm_title">Delete</h4>
                                </div>
                                <div class="modal-body" id="frm_body">
                                    Do you really want to delete?
                                    <input type="hidden" id="set_commondel_id">
                                    <input type="hidden" id="table_name">
                                </div>
                                <div class="modal-footer">
                                    <button style='margin-left:10px;' type="button" class="btn btn-primary col-sm-2 pull-right" id="frm_submit" onclick="delete_return();">Yes</button>
                                    <button type="button" class="btn btn-danger col-sm-2 pull-right" data-dismiss="modal" id="frm_cancel">No</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <!-- ./ Common Delete Popup /. -->