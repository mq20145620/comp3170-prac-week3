
attribute vec2 a_position;	/* vertex position */
attribute vec3 a_colour;	/* vertex colour */
uniform float u_angle; /* angle to rotate (in radians) */

varying vec3 v_colour;

void main() {

	/* pass colour info to fragment shader */
	v_colour = a_colour;

	/* turn 2D point into 3D homogeneous form by appending a 1 */

	vec3 position = vec3(a_position, 1);
	
	/*
	 * NOTE: matrices are written in _column order_ as:
	 *
	 * mat3(ix, iy, 0, jx, jy, 0, Tx, Ty, 1)
	 */
	 
	float s = sin(u_angle);
	float c = cos(u_angle);
	mat3 m = mat3(c,s,0,-s,c,0,0,0,1);

	/* rotate the point */
	
	position = m * position;

	/* convert to 4D homegeneous point */
	
    gl_Position = vec4(position.xy,0,1);
}

