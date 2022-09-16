<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dashboard</title>
        <link href="<?php echo $this->config->item('accet_url') ?>css/bootstrap.min.css" rel="stylesheet">
        <link href="<?php echo $this->config->item('accet_url') ?>font-awesome/css/font-awesome.css" rel="stylesheet">
        <link href="<?php echo $this->config->item('accet_url') ?>css/animate.css" rel="stylesheet">
        <link href="<?php echo $this->config->item('accet_url') ?>css/style.css" rel="stylesheet">
    </head>
    <body class="gray-bg">
        <!-- Fixed navbar -->
        <nav class="navbar navbar-default navbar-fixed-top">
          <div class="container">
          
 
						
						
                      </div>
        </nav>

    <br><br><br><br>

    <center>
        <?php if ($this->session->flashdata('message')): ?>

            <div class="alert alert-success">
                <button type="button" class="close" data-close="alert"></button>
                <?php echo $this->session->flashdata('message'); ?>
            </div>

        <?php endif; ?>
        <br><br><br><br><br><br>
        <div>
            <br>
            <div style="width:20%;">
                  <form  action="" method="post">
                    <input type="hidden" name="<?php echo $this->security->get_csrf_token_name(); ?>" value="<?php echo $this->security->get_csrf_hash(); ?>">

                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Username" value="<?php echo set_value('identity'); ?>" name='identity' id='identity' required="">
                        <?php echo form_error('identity', '<span class="err-msg">', '</span>') ?>
                    </div>

                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="Password" name='password' value="<?php echo set_value('password'); ?>" required="">
                        <?php echo form_error('password', '<span class="err-msg">', '</span>') ?>
                    </div>

                    <button type="submit" class="btn btn-primary block full-width m-b">Login</button>
                    <!-- <div>if not registered please <a href="<?php echo base_url() ?>register">click here</a> for registration</div> -->
                  </form>
            </div>
    </center>
    <!-- Mainly scripts -->
    <script src="<?php echo $this->config->item('accet_url') ?>js/jquery-2.1.1.js"></script>
    <script src="<?php echo $this->config->item('accet_url') ?>js/bootstrap.min.js"></script>
</body>
</html>